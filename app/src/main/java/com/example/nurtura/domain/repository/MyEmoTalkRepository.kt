package com.example.nurtura.domain.repository

import com.example.nurtura.data.model.EmotionDetectionResponse
import okhttp3.MultipartBody

interface MyEmoTalkRepository {
    suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse?
}
