package com.example.nurtura.cache

import com.example.nurtura.domain.model.Doctor
import com.example.nurtura.domain.model.Schedule
import com.example.nurtura.R

val doctorList = listOf(
    Doctor(
        id = 1,
        name = "Loraine Glory, Sp. Og.",
        image = R.drawable.doctor_1,
        clinic = "Daqu Clinic, Malang",
        rating = 4.5f,
        patientCount = 170,
        experience = 2,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "14:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "12:20",
                available = false
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "19:20",
                available = false
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "14:10",
                available = false
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "19:20",
                available = true
            )
        )
    ),
    Doctor(
        id = 2,
        name = "Drake Schopus, Sp. Og.",
        image = R.drawable.doctor_2,
        clinic = "Brone Clinic, Malang",
        rating = 4.7f,
        patientCount = 250,
        experience = 2,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "12:10",
                available = true
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "14:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "12:10",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "14:10",
                available = false
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "15:20",
                available = false
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "14:10",
                available = true
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "12:10",
                available = false
            )
        )
    ),
    Doctor(
        id = 3,
        name = "Getty Richea, Sp. Og.",
        image = R.drawable.doctor_3,
        clinic = "Tugu Hospital, Malang",
        rating = 4.9f,
        patientCount = 559,
        experience = 3,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "07:10",
                available = true
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "11:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "07:10",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "11:10",
                available = true
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "17:10",
                available = false
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "11:10",
                available = true
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "07:10",
                available = true
            )
        )
    ),
    Doctor(
        id = 4,
        name = "Yuselma Sulvei Sp. Og.",
        image = R.drawable.doctor_4,
        clinic = "Medika Clinic, Surabaya",
        rating = 4.7f,
        patientCount = 457,
        experience = 4,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "18:10",
                available = true
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "19:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "18:10",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "19:10",
                available = true
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "07:10",
                available = false
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "07:10",
                available = false
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "07:10",
                available = false
            )
        )
    ),
    Doctor(
        id = 5,
        name = "Ashkan Forzani, Sp. Og.",
        image = R.drawable.doctor_5,
        clinic = "Loyal Clinic, Jakarta",
        rating = 4.5f,
        patientCount = 180,
        experience = 2,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "14:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "09:20",
                available = true
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "14:10",
                available = false
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "19:10",
                available = false
            )
        )
    ),
    Doctor(
        id = 6,
        name = "Tahes Nappy, Sp. Og.",
        image = R.drawable.doctor_6,
        clinic = "Sore Hospital, Surabaya",
        rating = 4.9f,
        patientCount = 888,
        experience = 5,
        schedule = listOf(
            Schedule(
                day = "Sen",
                date = "2",
                time = "12:20",
                available = false
            ),
            Schedule(
                day = "Sel",
                date = "3",
                time = "14:10",
                available = true
            ),
            Schedule(
                day = "Rab",
                date = "4",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Kam",
                date = "5",
                time = "12:20",
                available = true
            ),
            Schedule(
                day = "Jum",
                date = "6",
                time = "19:20",
                available = true
            ),
            Schedule(
                day = "Sab",
                date = "7",
                time = "14:10",
                available = false
            ),
            Schedule(
                day = "Min",
                date = "8",
                time = "19:20",
                available = false
            )
        )
    )
)