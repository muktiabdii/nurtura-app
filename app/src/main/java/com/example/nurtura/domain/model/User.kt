package com.example.nurtura.domain.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val pregnancyAge: Int = 0,
    val healthNotes: String = "",
    val location: String = ""
)
