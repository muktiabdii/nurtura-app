package com.example.nurtura.domain.model

data class Schedule(
    val day: String,
    val date: String,
    val time: String,
    val available: Boolean
)

data class Doctor(
    val id: Int,
    val name: String,
    val number: Long,
    val image: Int,
    val clinic: String,
    val rating: Float,
    val patientCount: Int,
    val experience: Int,
    val schedule: List<Schedule>
)
