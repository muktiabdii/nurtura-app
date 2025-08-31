package com.example.nurtura.domain.repository

import com.example.nurtura.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserFromRemote(uid: String): User?
    suspend fun saveUserToCache(uid: String, name: String, email: String)
    fun getUserUidFlow(): Flow<String?>
    suspend fun logout()
    suspend fun editProfile(uid: String, name: String, email: String): Boolean
    suspend fun deleteAccount(uid: String)
}