package com.example.nurtura.data.repository

import android.content.Context
import com.example.nurtura.R
import com.example.nurtura.cache.UserData
import com.example.nurtura.data.model.EmotionDetectionResponse
import com.example.nurtura.data.remote.api.ApiEmotionDetectionService
import com.example.nurtura.data.remote.firebase.FirebaseProvider
import com.example.nurtura.domain.model.Food
import com.example.nurtura.domain.repository.MyEmoTalkRepository
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody

class MyEmoTalkRepositoryImpl(private val context: Context) : MyEmoTalkRepository {

    val db = FirebaseProvider.database
    val user = UserData.uid

    // function to detect emotion
    override suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse? {
        val response = ApiEmotionDetectionService.instance.detectEmotion(file)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    // function to get food detail
    override suspend fun getFoodDetail(id: String): Food? {
        return try {
            val snapshot = db.child("users")
                .child(user)
                .child("foodRecommendations")
                .child(id)
                .get()
                .await()

            val food = snapshot.getValue(Food::class.java)

            val imageRes = context.resources.getIdentifier(
                food?.imageResId ?: "",
                "drawable",
                context.packageName
            ).takeIf { it != 0 } ?: R.drawable.food_1

            food?.copy(image = imageRes)
        } catch (e: Exception) {
            null
        }
    }

    // function to get all of food recommendations
    override suspend fun getAllFoodRecommendations(): List<Food> {
        return try {
            val snapshot = db.child("users")
                .child(user)
                .child("foodRecommendations")
                .get()
                .await()

            val foods = mutableListOf<Food>()

            snapshot.children.forEach { child ->
                val food = child.getValue(Food::class.java)
                val imageRes = context.resources.getIdentifier(
                    food?.imageResId ?: "",
                    "drawable",
                    context.packageName
                ).takeIf { it != 0 } ?: R.drawable.food_1

                food?.let { foods.add(it.copy(image = imageRes)) }
            }

            foods
        } catch (e: Exception) {
            emptyList()
        }
    }

}
