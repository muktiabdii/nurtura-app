package com.example.nurtura.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nurtura.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.nurtura.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

// general state
sealed class State {
    object Idle : State()
    object Loading : State()
    object Success : State()
    data class Error(val message: String) : State()
}

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {
    // login state
    private val _loginState = MutableStateFlow<State>(State.Idle)
    val loginState: StateFlow<State> = _loginState

    // register state
    private val _registerState = MutableStateFlow<State>(State.Idle)
    val registerState: StateFlow<State> = _registerState

    // forgot password state
    private val _forgotPasswordState = MutableStateFlow<State>(State.Idle)
    val forgotPasswordState: StateFlow<State> = _forgotPasswordState

    // reset state
    fun resetLoginState() {
        _loginState.value = State.Idle
    }

    fun resetRegisterState() {
        _registerState.value = State.Idle
    }

    fun resetForgotPasswordState() {
        _forgotPasswordState.value = State.Idle
    }

    // function login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = State.Loading
            val result = authUseCase.login(email, password)
            result.onSuccess { uid ->
                if (uid.isNotEmpty()) {
                    loadUser(uid)
                }

                _loginState.value = State.Success
            }.onFailure { e ->
                _loginState.value = State.Error(e.message ?: "Login gagal")
            }
        }
    }

    // function load user
    suspend fun loadUser(uid: String) {
        val user = userUseCase.getUserFromRemote(uid)
        if (user != null) {
            userUseCase.saveUserToCache(user.uid, user.name, user.email)
        }
    }

    // function register
    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModelScope.launch {
            _registerState.value = State.Loading
            authUseCase.register(name, email, password, passwordConfirmation) { success, message ->
                _registerState.value = if (success) State.Success else State.Error(message ?: "Register gagal")
            }
        }
    }

    // function forgot password
    fun forgotPassword(
        email: String
    ) {
        viewModelScope.launch {
            _forgotPasswordState.value = State.Loading
            authUseCase.forgotPassword(email) { success, message ->
                _forgotPasswordState.value = if (success) State.Success else State.Error(message ?: "Forgot password gagal")
            }
        }
    }

    class Factory(
        private val authUseCase: AuthUseCase,
        private val userUseCase: UserUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authUseCase, userUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}