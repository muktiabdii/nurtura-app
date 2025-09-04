package com.example.nurtura.domain.repository

import com.example.nurtura.data.model.EmotionDetectionResponse
import com.example.nurtura.domain.model.Food
import okhttp3.MultipartBody

interface MyEmoTalkRepository {
    suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse?
    suspend fun getFoodDetail(id: String): Food?
}
