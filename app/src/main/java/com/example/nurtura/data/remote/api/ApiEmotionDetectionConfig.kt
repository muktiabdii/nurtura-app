package com.example.nurtura.data.remote.api

import com.example.nurtura.data.model.EmotionDetectionResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiEmotionDetectionConfig {
    @Multipart
    @POST("predict/")
    suspend fun detectEmotion(
        @Part file: MultipartBody.Part
    ): Response<EmotionDetectionResponse>
}