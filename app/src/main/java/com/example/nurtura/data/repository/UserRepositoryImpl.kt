package com.example.nurtura.data.repository

import com.example.nurtura.cache.UserData
import com.example.nurtura.data.datastore.PreferencesManager
import com.example.nurtura.data.remote.firebase.FirebaseProvider
import com.example.nurtura.domain.model.User
import com.example.nurtura.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val preferencesManager: PreferencesManager) : UserRepository {

    private val database = FirebaseProvider.database
    private val auth = FirebaseProvider.auth

    // function untuk mendapatkan user dari remote
    override suspend fun getUserFromRemote(uid: String): User? {
        val snapshot = database.child("users").child(uid).get().await()
        return snapshot.getValue(User::class.java)
    }

    // function untuk mendapatkan user dari cache
    override suspend fun saveUserToCache(uid: String, name: String, email: String, pregnancyAge: Int, healthNotes: String, location: String) {
        preferencesManager.saveUser(uid, name, email, pregnancyAge, healthNotes, location)
        UserData.set(uid, name, email, pregnancyAge, healthNotes, location)
    }

    // function untuk mendapatkan user uid dari cache
    override fun getUserUidFlow(): Flow<String?> {
        return preferencesManager.userUid
    }

    // function untuk menghapus user dari cache
    override suspend fun logout() {
        preferencesManager.clearUser()
        UserData.clear()
    }

    // function untuk edit profile
    override suspend fun editProfile(uid: String, updates: Map<String, Any>): Boolean {
        try {
            // update firebase
            database.child("users").child(uid).updateChildren(updates).await()

            // update datastore & cache
            val userCache = UserData
            val newData = mapOf(
                "uid" to (updates["uid"] ?: userCache.uid),
                "name" to (updates["name"] ?: userCache.name),
                "email" to (updates["email"] ?: userCache.email),
                "pregnancyAge" to (updates["pregnancyAge"] ?: userCache.pregnancyAge),
                "healthNotes" to (updates["healthNotes"] ?: userCache.healthNotes),
                "location" to (updates["location"] ?: userCache.location)
            )

            preferencesManager.saveUser(
                newData["uid"] as String,
                newData["name"] as String,
                newData["email"] as String,
                newData["pregnancyAge"] as Int,
                newData["healthNotes"] as String,
                newData["location"] as String
            )

            UserData.set(
                newData["uid"] as String,
                newData["name"] as String,
                newData["email"] as String,
                newData["pregnancyAge"] as Int,
                newData["healthNotes"] as String,
                newData["location"] as String
            )

            return true
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }


    // function untuk hapus akun
    override suspend fun deleteAccount(uid: String) {
        try {
            // hapus user dari database
            database.child("users").child(uid).removeValue().await()

            // hapus user dari datastore
            preferencesManager.clearUser()

            // hapus user dari cache
            UserData.clear()

            // terakhir hapus user dari firebase auth
            auth.currentUser?.delete()?.await()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}