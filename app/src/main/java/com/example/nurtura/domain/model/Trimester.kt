package com.example.nurtura.domain.model

data class Trimester(
    val trimesterNumber: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val fetalDevelopment: List<String>,
    val tips: String
)