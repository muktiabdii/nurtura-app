package com.example.nurtura.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CloudinaryService {
    private const val BASE_URL = "https://api.cloudinary.com/v1_1/dvwbrl4el/"

    val instance: CloudinaryConfig by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CloudinaryConfig::class.java)
    }
}