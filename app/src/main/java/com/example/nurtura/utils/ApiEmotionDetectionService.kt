package com.example.nurtura.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object ApiEmotionDetectionService {
    private const val BASE_URL = "https://web-production-dc48.up.railway.app/"

    val instance: ApiEmotionDetectionConfig by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiEmotionDetectionConfig::class.java)
    }
}