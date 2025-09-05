package com.example.nurtura.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nurtura.cache.UserData
import com.example.nurtura.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Profile state
sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    object Success : ProfileState()
    data class Error(val message: String) : ProfileState()
}

// User state
data class UserState(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val pregnancyAge: Int = 0,
    val healthNotes: String = "",
    val location: String = ""
)

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    // profile state
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Idle)
    val profileState: StateFlow<ProfileState> = _profileState

    // user state
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    // function reset profile state
    fun resetProfileState() {
        _profileState.value = ProfileState.Idle
    }

    init {
        loadUserData()
    }

    // load user data dari cache
    fun loadUserData() {
        _userState.value = UserState(
            uid = UserData.uid,
            name = UserData.name,
            email = UserData.email,
            pregnancyAge = UserData.pregnancyAge,
            healthNotes = UserData.healthNotes,
            location = UserData.location
        )
    }

    fun refreshUserData() {
        loadUserData()
    }

    // edit profile generic dengan ProfileState
    fun editProfile(updates: Map<String, Any>) {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading
            try {
                val uid = _userState.value.uid
                val result = userUseCase.editProfile(uid, updates)
                if (result) {
                    // update user state
                    var newState = _userState.value
                    updates["name"]?.let { newState = newState.copy(name = it as String) }
                    updates["email"]?.let { newState = newState.copy(email = it as String) }
                    updates["pregnancyAge"]?.let { newState = newState.copy(pregnancyAge = it as Int) }
                    updates["healthNotes"]?.let { newState = newState.copy(healthNotes = it as String) }
                    updates["location"]?.let { newState = newState.copy(location = it as String) }

                    _userState.value = newState

                    // update cache
                    UserData.set(
                        uid,
                        newState.name,
                        newState.email,
                        newState.pregnancyAge,
                        newState.healthNotes,
                        newState.location
                    )

                    _profileState.value = ProfileState.Success
                } else {
                    _profileState.value = ProfileState.Error("Gagal update profile")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _profileState.value = ProfileState.Error(e.localizedMessage ?: "Terjadi kesalahan")
            }
        }
    }

    // helper functions untuk UI
    fun editAccount(name: String, email: String) {
        editProfile(mapOf("name" to name, "email" to email))
    }

    fun editPersonalData(pregnancyAge: Int, healthNotes: String, location: String) {
        editProfile(
            mapOf(
                "pregnancyAge" to pregnancyAge,
                "healthNotes" to healthNotes,
                "location" to location
            )
        )
    }

    // function logout
    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }

    // function delete account
    fun deleteAccount() {
        viewModelScope.launch {
            try {
                val uid = _userState.value.uid
                userUseCase.deleteAccount(uid)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val userUseCase: UserUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(userUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
