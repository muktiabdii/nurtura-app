package com.example.nurtura.domain.usecase

import com.example.nurtura.data.model.EmotionDetectionResponse
import com.example.nurtura.domain.model.Food
import com.example.nurtura.domain.repository.MyEmoTalkRepository
import okhttp3.MultipartBody

class MyEmoTalkUseCase(private val repository: MyEmoTalkRepository) {
    suspend operator fun invoke(file: MultipartBody.Part): EmotionDetectionResponse? {
        return repository.detectEmotion(file)
    }

    suspend fun getFoodDetail(id: String): Food? {
        return repository.getFoodDetail(id)
    }

    suspend fun getAllFoodRecommendations(): List<Food> {
        return repository.getAllFoodRecommendations()
    }
}
