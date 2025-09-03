package com.example.nurtura.domain.model

data class Food(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val rating: Float,
    val image: Int
)
