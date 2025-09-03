package com.example.nurtura.cache

object UserData {
    var uid: String = ""
    var name: String = ""
    var email: String = ""
    var pregnancyAge: Int = 0
    var healthNotes: String = ""
    var location: String = ""

    fun set(uid: String, name: String, email: String, pregnancyAge: Int, healthNotes: String, location: String) {
        this.uid = uid
        this.name = name
        this.email = email
        this.pregnancyAge = pregnancyAge
        this.healthNotes = healthNotes
        this.location = location
    }

    fun clear() {
        uid = ""
        name = ""
        email = ""
        pregnancyAge = 0
        healthNotes = ""
        location = ""
    }
}
