package com.example.nurtura.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// datastore preferences
private val Context.dataStore by preferencesDataStore("app_preferences")

class PreferencesManager(private val context: Context) {

    companion object {
        private val KEY_UID = stringPreferencesKey("uid")
        private val KEY_NAME = stringPreferencesKey("name")
        private val KEY_EMAIL = stringPreferencesKey("email")
        private val KEY_PREGNANCY_AGE = intPreferencesKey("pregnancy_age")
        private val KEY_HEALTH_NOTES = stringPreferencesKey("health_notes")
        private val KEY_LOCATION = stringPreferencesKey("location")
        private val KEY_ONBOARDING_SHOWN = booleanPreferencesKey("is_onboarding_shown")
    }

    // save user info
    suspend fun saveUser(uid: String, name: String, email: String, pregnancyAge: Int, healthNotes: String, location: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_UID] = uid
            preferences[KEY_NAME] = name
            preferences[KEY_EMAIL] = email
            preferences[KEY_PREGNANCY_AGE] = pregnancyAge
            preferences[KEY_HEALTH_NOTES] = healthNotes
            preferences[KEY_LOCATION] = location
        }
    }

    // clear user info
    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_UID)
            preferences.remove(KEY_NAME)
            preferences.remove(KEY_EMAIL)
            preferences.remove(KEY_PREGNANCY_AGE)
            preferences.remove(KEY_HEALTH_NOTES)
            preferences.remove(KEY_LOCATION)
        }
    }

    // get user uid flow
    val userUid: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_UID]
    }

    // get user name flow
    val userName: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_NAME]
    }

    // get user email flow
    val userEmail: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_EMAIL]
    }

    // save onboarding state
    suspend fun setOnboardingShown(shown: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_ONBOARDING_SHOWN] = shown
        }
    }

    // get onboarding state flow
    val isOnboardingShown: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_ONBOARDING_SHOWN] ?: false
    }
}
