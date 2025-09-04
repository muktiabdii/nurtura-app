package com.example.nurtura.domain.usecase

import com.example.nurtura.domain.repository.GeminiRepository

class GeminiUseCase(private val repository: GeminiRepository) {

    suspend fun getSaranGemini(emotion: String, trimester: Int): Pair<Int?, Boolean> {
        return repository.getGeminiReply(emotion, trimester)
    }
}