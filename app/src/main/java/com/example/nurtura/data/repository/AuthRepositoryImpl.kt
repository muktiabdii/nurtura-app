package com.example.nurtura.data.repository

import com.example.nurtura.data.remote.firebase.FirebaseProvider
import com.example.nurtura.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl: AuthRepository {

    // inisiasi firebase auth dan database
    private val auth = FirebaseProvider.auth
    private val database = FirebaseProvider.database

    // function login
    override suspend fun login(
        email: String,
        password: String
    ): String {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID tidak ditemukan")
            return uid
        }

        catch (e: Exception) {
            throw Exception(getLocalizedErrorMessage(e.message))
        }
    }


    // function register
    override fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = database.child("users").child(userId)

                        // menyimpan data pengguna ke database
                        val userData = hashMapOf(
                            "uid" to userId,
                            "name" to name,
                            "email" to email
                        )
                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }
                            .addOnFailureListener { exception ->

                                // jika gagal, hapus akun pengguna
                                user.delete().addOnCompleteListener {
                                    onResult(false, exception.message)
                                }
                            }
                    }
                }

                // jika registrasi gagal, kirim pesan error
                else {
                    val errorMessage = getLocalizedErrorMessage(task.exception?.message)
                    onResult(false, errorMessage)
                }
            }
    }

    // function forgot password
    override fun forgotPassword(
        email: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                else {
                    val errorMessage = getLocalizedErrorMessage(task.exception?.message)
                    onResult(false, errorMessage)
                }
            }
    }

    // function untuk mendapatkan pesan error yang sesuai
    private fun getLocalizedErrorMessage(errorMessage: String?): String {
        return when {
            errorMessage?.contains("The email address is badly formatted") == true ->
                "Format email salah"

            errorMessage?.contains("The supplied auth credential is incorrect, malformed or has expired.") == true ->
                "Silahkan periksa kembali email dan password Anda"

            errorMessage?.contains("A network error") == true ->
                "Terjadi kesalahan jaringan"

            errorMessage?.contains("The email address is already in use by another account") == true ->
                "Email sudah terdaftar"

            else -> errorMessage ?: "Terjadi kesalahan saat login"
        }
    }
}