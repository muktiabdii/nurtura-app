package com.example.nurtura.ui.mydoc

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nurtura.domain.model.CloudinaryResponse
import com.example.nurtura.domain.usecase.CloudinaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyDocViewModel(private val cloudinaryUseCase: CloudinaryUseCase) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // function upload image
    fun uploadImage(uri: Uri) {
        viewModelScope.launch {
            _loading.value = true
            val result: CloudinaryResponse? = cloudinaryUseCase(uri)
            _imageUrl.value = result?.secureUrl
            _loading.value = false
        }
    }

    class Factory(private val cloudinaryUseCase: CloudinaryUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyDocViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyDocViewModel(cloudinaryUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}