package com.example.nurtura.domain.repository

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): String
    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        pregnancyAge: Int,
        healthNotes: String,
        location: String,
        onResult: (Boolean, String?) -> Unit
    )
    fun forgotPassword(
        email: String,
        onResult: (Boolean, String?) -> Unit
    )
}