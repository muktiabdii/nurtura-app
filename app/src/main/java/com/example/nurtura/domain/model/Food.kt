package com.example.nurtura.domain.model

data class Food(
    val id: String? = null,
    val name: String? = null,
    val imageResId: String? = null,
    val image: Int = 0,
    val rating: Float? = null,
    val ingredients: List<String>? = null,
    val steps: List<String>? = null
)
