package com.example.nurtura.ui.myemotalk

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nurtura.domain.usecase.MyEmoTalkUseCase
import com.example.nurtura.domain.usecase.OnBoardingUseCase
import com.example.nurtura.domain.usecase.UserUseCase
import com.example.nurtura.ui.splash.SplashViewModel
import com.example.nurtura.utils.AudioWavRecorder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MyEmoTalkViewModel(private val myEmoTalkUseCase: MyEmoTalkUseCase) : ViewModel() {

    private var recorder: AudioWavRecorder? = null
    private var audioFile: File? = null

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _emotion = MutableStateFlow<String?>(null)
    val emotion: StateFlow<String?> = _emotion

    fun startRecording(context: Context) {
        audioFile = File(context.cacheDir, "recorded_audio.wav")
        recorder = AudioWavRecorder(audioFile!!)
        recorder?.startRecording(context)
        _isRecording.value = true
    }

    fun stopRecordingAndSend() {
        recorder?.stopRecording()
        _isRecording.value = false
        _isLoading.value = true

        audioFile?.let { file ->
            val requestFile = file.asRequestBody("audio/wav".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            viewModelScope.launch {
                try {
                    val result = myEmoTalkUseCase(body)
                    _emotion.value = result?.emotion
                    Log.d("MyEmoTalkViewModel", "Emotion: ${result?.emotion}")
                } finally {
                    _isLoading.value = false
                }
            }
        } ?: run {
            _isLoading.value = false
        }
    }

    class Factory(private val myEmoTalkUseCase: MyEmoTalkUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyEmoTalkViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyEmoTalkViewModel(myEmoTalkUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
