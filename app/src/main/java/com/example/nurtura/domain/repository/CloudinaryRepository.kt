package com.example.nurtura.domain.repository

import android.net.Uri
import com.example.nurtura.domain.model.CloudinaryResponse

interface CloudinaryRepository {
    suspend fun uploadImage(uri: Uri): CloudinaryResponse?
}