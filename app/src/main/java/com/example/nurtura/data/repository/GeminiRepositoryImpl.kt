package com.example.nurtura.data.repository

import com.example.nurtura.BuildConfig
import com.example.nurtura.R
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

    override suspend fun getGeminiReply(emotion: String, trimester: Int): Pair<Int?, Boolean> {
        return try {
            val finalPrompt = buildString {
                appendLine("Kamu adalah seorang ahli nutrisi untuk ibu yang sedang mengandung dengan trimester $trimester.")
                appendLine("Saat ini user merasa $emotion.")
                appendLine("Berikan 1 rekomendasi makanan sehat dalam format JSON object dengan struktur berikut:")
                appendLine("{")
                appendLine("  \"id\": int,")
                appendLine("  \"name\": \"string\",")
                appendLine("  \"ingredients\": [\"string\"],")
                appendLine("  \"steps\": [\"string\"],")
                appendLine("  \"rating\": float")
                appendLine("}")
                appendLine("Jangan tambahkan penjelasan lain di luar JSON. Hanya JSON object valid.")
            }

            val request = GeminiRequest(
                contents = listOf(
                    GeminiRequest.Content(
                        parts = listOf(GeminiRequest.Part(text = finalPrompt))
                    )
                )
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
            val food: Food? = try {
                gson.fromJson(cleanJson, Food::class.java)
            }

            catch (e: Exception) {
                null
            }

            val drawables = listOf(
                R.drawable.food_1,
                R.drawable.food_2,
                R.drawable.food_3,
                R.drawable.food_4,
                R.drawable.food_5,
                R.drawable.food_6
            )
            val foodWithImage = food?.copy(image = drawables.random())

            val uid = UserData.uid

            if (foodWithImage != null && uid.isNotBlank()) {
                db.child("users")
                    .child(uid)
                    .child("foodRecommendation")
                    .child(foodWithImage.id.toString())
                    .setValue(foodWithImage)

                UserData.foodRecommendations[foodWithImage.id] = foodWithImage
            }

            Pair(foodWithImage?.id, true)

        }

        catch (e: Exception) {
            Pair(null, false)
        }
    }
}
