package com.example.nurtura.data.repository

import com.example.nurtura.data.model.EmotionDetectionResponse
import com.example.nurtura.data.remote.api.ApiEmotionDetectionService
import com.example.nurtura.domain.repository.MyEmoTalkRepository
import okhttp3.MultipartBody

class MyEmoTalkRepositoryImpl : MyEmoTalkRepository {
    override suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse? {
        val response = ApiEmotionDetectionService.instance.detectEmotion(file)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
