package com.example.nurtura.domain.usecase

import android.net.Uri
import com.example.nurtura.domain.model.CloudinaryResponse
import com.example.nurtura.domain.repository.CloudinaryRepository

class CloudinaryUseCase(private val repository: CloudinaryRepository) {

    // function upload image
    suspend operator fun invoke(uri: Uri): CloudinaryResponse? {
        return repository.uploadImage(uri)
    }
}