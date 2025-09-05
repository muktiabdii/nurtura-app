package com.example.nurtura.domain.usecase

import com.example.nurtura.domain.model.User
import com.example.nurtura.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(private val userRepository: UserRepository) {

    // function untuk mendapatkan user dari remote
    suspend fun getUserFromRemote(uid: String): User? {
        return userRepository.getUserFromRemote(uid)
    }

    // function untuk mendapatkan user dari cache
    suspend fun saveUserToCache(uid: String, name: String, email: String, pregnancyAge: Int, healthNotes: String, location: String) {
        userRepository.saveUserToCache(uid, name, email, pregnancyAge, healthNotes, location)
    }

    // function untuk mendapatkan user uid dari cache
    fun getUserUidFlow(): Flow<String?> {
        return userRepository.getUserUidFlow()
    }

    // function logout
    suspend fun logout() {
        userRepository.logout()
    }

    // function untuk edit profile
    suspend fun editProfile(uid: String, updates: Map<String, Any>): Boolean {
        validateProfileUpdates(updates)
        return userRepository.editProfile(uid, updates)
    }

    // function untuk hapus akun
    suspend fun deleteAccount(uid: String) {
        userRepository.deleteAccount(uid)
    }

    // function untuk pengecekan
    private fun validateProfileUpdates(updates: Map<String, Any>) {

        // validasi email
        updates["email"]?.let { email ->
            if (email !is String || !email.contains("@") || email.isBlank()) {
                throw IllegalArgumentException("Email tidak valid")
            }
        }

        // validasi nama
        updates["name"]?.let { name ->
            if (name !is String || name.isBlank()) {
                throw IllegalArgumentException("Nama tidak boleh kosong")
            }
        }

        // validasi pregnancyAge
        updates["pregnancyAge"]?.let { age ->
            if (age !is Int || age < 1 || age > 3) {
                throw IllegalArgumentException("Trimester tidak valid")
            }
        }

        // validasi location
        updates["location"]?.let { loc ->
            if (loc !is String || loc.isBlank()) {
                throw IllegalArgumentException("Lokasi tidak boleh kosong")
            }
        }

        // validasi healthNotes (optional, max 500 karakter)
        updates["healthNotes"]?.let { notes ->
            if (notes !is String || notes.length > 500) {
                throw IllegalArgumentException("Catatan kesehatan terlalu panjang")
            }
        }
    }
}