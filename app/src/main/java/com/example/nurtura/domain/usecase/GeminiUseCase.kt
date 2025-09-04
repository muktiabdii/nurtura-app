package com.example.nurtura.domain.usecase

import com.example.nurtura.domain.repository.GeminiRepository

class GeminiUseCase(private val repository: GeminiRepository) {

    suspend fun getSaranGemini(emotion: String): Pair<String?, Boolean> {
        return repository.getGeminiReply(emotion)
    }
}