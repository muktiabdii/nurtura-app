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
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

// state register
data class RegisterUiState(
    val step: Int = 1,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val pregnancyAge: Int = 0,
    val healthNotes: String = "",
    val location: String = "",
    val authState: AuthState = AuthState.Idle
)

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {
    // login state
    private val _loginAuthState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginAuthState: StateFlow<AuthState> = _loginAuthState

    // register state
    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    // forgot password state
    private val _forgotPasswordAuthState = MutableStateFlow<AuthState>(AuthState.Idle)
    val forgotPasswordAuthState: StateFlow<AuthState> = _forgotPasswordAuthState

    // update register state
    fun updateUsername(username: String) {
        _registerUiState.value = _registerUiState.value.copy(username = username)
    }

    fun updateEmail(email: String) {
        _registerUiState.value = _registerUiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _registerUiState.value = _registerUiState.value.copy(password = password)
    }

    fun updatePasswordConfirmation(passwordConfirmation: String) {
        _registerUiState.value = _registerUiState.value.copy(passwordConfirmation = passwordConfirmation)
    }

    fun updatePregnancyAge(pregnancyAge: Int) {
        _registerUiState.value = _registerUiState.value.copy(pregnancyAge = pregnancyAge)
    }

    fun updateHealthNotes(healthNotes: String) {
        _registerUiState.value = _registerUiState.value.copy(healthNotes = healthNotes)
    }

    fun updateLocation(location: String) {
        _registerUiState.value = _registerUiState.value.copy(location = location)
    }

    // navigasi step
    fun nextStep() {
        val current = _registerUiState.value.step
        if (current < 3) {
            _registerUiState.value = _registerUiState.value.copy(step = current + 1)
        }
    }

    fun previousStep() {
        val current = _registerUiState.value.step
        if (current > 1) {
            _registerUiState.value = _registerUiState.value.copy(step = current - 1)
        }
    }

    fun goToStep(step: Int) {
        if (step in 1..3) {
            _registerUiState.value = _registerUiState.value.copy(step = step)
        }
    }

    // reset state
    fun resetLoginState() {
        _loginAuthState.value = AuthState.Idle
    }

    fun resetRegisterState() {
        _registerUiState.value = RegisterUiState()
    }

    fun resetForgotPasswordState() {
        _forgotPasswordAuthState.value = AuthState.Idle
    }

    // function login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginAuthState.value = AuthState.Loading
            val result = authUseCase.login(email, password)
            result.onSuccess { uid ->
                if (uid.isNotEmpty()) {
                    loadUser(uid)
                }

                _loginAuthState.value = AuthState.Success
            }.onFailure { e ->
                _loginAuthState.value = AuthState.Error(e.message ?: "Login gagal")
            }
        }
    }

    // function load user
    suspend fun loadUser(uid: String) {
        val user = userUseCase.getUserFromRemote(uid)
        if (user != null) {
            userUseCase.saveUserToCache(user.uid, user.name, user.email, user.pregnancyAge, user.healthNotes, user.location)
        }
    }

    // function register
    fun register() {
        viewModelScope.launch {
            _registerUiState.value = _registerUiState.value.copy(authState = AuthState.Loading)
            val currentState = _registerUiState.value
            authUseCase.register(
                name = currentState.username,
                email = currentState.email,
                password = currentState.password,
                passwordConfirmation = currentState.passwordConfirmation,
                pregnancyAge = currentState.pregnancyAge,
                healthNotes = currentState.healthNotes,
                location = currentState.location
            ) { success, message ->
                _registerUiState.value = _registerUiState.value.copy(
                    authState = if (success) AuthState.Success else AuthState.Error(message ?: "Register gagal")
                )
            }
        }
    }

    // function forgot password
    fun forgotPassword(
        email: String
    ) {
        viewModelScope.launch {
            _forgotPasswordAuthState.value = AuthState.Loading
            authUseCase.forgotPassword(email) { success, message ->
                _forgotPasswordAuthState.value = if (success) AuthState.Success else AuthState.Error(message ?: "Forgot password gagal")
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