package com.example.nurtura.data.repository

import com.example.nurtura.BuildConfig
import com.example.nurtura.cache.UserData
import com.example.nurtura.data.model.GeminiRequest
import com.example.nurtura.data.model.GeminiResponse
import com.example.nurtura.data.remote.firebase.FirebaseProvider
import com.example.nurtura.data.remote.gemini.GeminiProvider
import com.example.nurtura.domain.model.Food
import com.example.nurtura.domain.repository.GeminiRepository
import com.google.gson.Gson

class GeminiRepositoryImpl() : GeminiRepository {

    private val db = FirebaseProvider.database
    private val geminiKey = BuildConfig.GEMINI_API_KEY
    private val trimester = UserData.pregnancyAge
    private val healthNotes = UserData.healthNotes

    // function to get reply from gemini
    override suspend fun getGeminiReply(emotion: String): Pair<String?, Boolean> {
        return try {
            val finalPrompt = buildString {
                appendLine("Kamu adalah seorang ahli nutrisi untuk ibu yang sedang mengandung dengan trimester $trimester dan dengan catatan kesehatan $healthNotes.")
                appendLine("Saat ini ibu merasa $emotion.")
                appendLine("Berikan 1 rekomendasi makanan sehat dalam format JSON object dengan struktur berikut:")
                appendLine("{")
                appendLine("  \"id\": \"string\",")
                appendLine("  \"name\": \"string\",")
                appendLine("  \"ingredients\": [\"string\"],")
                appendLine("  \"steps\": [\"string\"],")
                appendLine("  \"rating\": float")
                appendLine("}")
                appendLine("Jangan tambahkan penjelasan lain di luar JSON. Hanya JSON object valid.")
            }

            val request = GeminiRequest(
                contents = listOf(GeminiRequest.Content(
                    parts = listOf(GeminiRequest.Part(text = finalPrompt))
                ))
            )

            val response: GeminiResponse = GeminiProvider.service.sendPrompt(
                apiKey = geminiKey,
                request = request
            )

            val replyText = response.candidates?.firstOrNull()
                ?.content?.parts?.firstOrNull()?.text ?: "{}"

            val cleanJson = replyText
                .replace("```json", "")
                .replace("```", "")
                .trim()

            val gson = Gson()
            val food: Food? = try { gson.fromJson(cleanJson, Food::class.java) } catch (e: Exception) { null }

            val drawables = listOf("food_1","food_2","food_3","food_4","food_5","food_6")

            val foodId = food?.id.takeIf { !it.isNullOrBlank() } ?: db.push().key

            val foodWithImage = food?.copy(
                id = foodId ?: "",
                imageResId = drawables.random()
            )

            foodWithImage?.id?.let { idNonNull ->
                val uid = UserData.uid
                if (!uid.isNullOrBlank()) {
                    db.child("users")
                        .child(uid)
                        .child("foodRecommendations")
                        .child(idNonNull)
                        .setValue(foodWithImage)
                }
            }


            Pair(foodWithImage?.id, true)

        } catch (e: Exception) {
            Pair(null, false)
        }
    }

}
