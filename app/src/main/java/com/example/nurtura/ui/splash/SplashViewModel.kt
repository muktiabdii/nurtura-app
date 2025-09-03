package com.example.nurtura.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nurtura.domain.usecase.OnBoardingUseCase
import com.example.nurtura.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userUseCase: UserUseCase,
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    // function untuk mendapatkan state onboarding
    fun isOnBoardingShown(): Flow<Boolean> {
        return onBoardingUseCase.getOnBoardingState()
    }

    // function untuk mengubah state onboarding
    fun setOnBoardingShown() {
        viewModelScope.launch {
            onBoardingUseCase.setOnBoardingState(true)
        }
    }

    // function untuk mendapatkan user uid dari cache
    fun getUserUidFlow(): Flow<String?> {
        return userUseCase.getUserUidFlow()
    }

    // function untuk load user
    suspend fun loadUser(uid: String) {
        val user = userUseCase.getUserFromRemote(uid)
        if (user != null) {
            userUseCase.saveUserToCache(user.uid, user.name, user.email, user.pregnancyAge, user.healthNotes, user.location)
        }
    }

    class Factory(
        private val userUseCase: UserUseCase,
        private val onBoardingUseCase: OnBoardingUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SplashViewModel(userUseCase, onBoardingUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}