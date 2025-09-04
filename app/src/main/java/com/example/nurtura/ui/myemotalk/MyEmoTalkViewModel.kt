package com.example.nurtura.ui.myemotalk

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nurtura.cache.UserData
import com.example.nurtura.domain.model.Food
import com.example.nurtura.domain.usecase.GeminiUseCase
import com.example.nurtura.domain.usecase.MyEmoTalkUseCase
import com.example.nurtura.utils.AudioWavRecorder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MyEmoTalkViewModel(
    private val myEmoTalkUseCase: MyEmoTalkUseCase,
    private val geminiUseCase: GeminiUseCase
) : ViewModel() {

    private var recorder: AudioWavRecorder? = null
    private var audioFile: File? = null

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _emotion = MutableStateFlow<String?>(null)
    val emotion: StateFlow<String?> = _emotion

    private val _foodId = MutableStateFlow<String?>(null)
    val foodId: StateFlow<String?> = _foodId

    private val _foodDetail = MutableStateFlow<Food?>(null)
    val foodDetail: StateFlow<Food?> = _foodDetail

    fun resetFoodId() {
        _foodId.value = null
    }

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
                    result?.emotion?.let { emo ->
                        _emotion.value = emo
                        getFoodRecommendation(emo)
                    } ?: run {
                        _isLoading.value = false
                    }
                } catch (e: Exception) {
                    _isLoading.value = false
                }
            }
        } ?: run {
            _isLoading.value = false
        }
    }

    fun getFoodRecommendation(emotion: String) {
        viewModelScope.launch {
            val (id, success) = geminiUseCase.getSaranGemini(emotion)
            if (success && id != null) {
                _foodId.value = id
            }
            _isLoading.value = false
        }
    }

    fun loadFoodDetail(foodId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val food = myEmoTalkUseCase.getFoodDetail(foodId)
            _foodDetail.value = food
            _isLoading.value = false
        }
    }

    class Factory(
        private val myEmoTalkUseCase: MyEmoTalkUseCase,
        private val geminiUseCase: GeminiUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyEmoTalkViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyEmoTalkViewModel(myEmoTalkUseCase, geminiUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}