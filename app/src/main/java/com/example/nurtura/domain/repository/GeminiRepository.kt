package com.example.nurtura.domain.repository

interface GeminiRepository {
    suspend fun getGeminiReply(emotion: String, trimester: Int): Pair<Int?, Boolean>
}