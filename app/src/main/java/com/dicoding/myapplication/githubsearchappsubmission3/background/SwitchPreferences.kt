package com.dicoding.myapplication.githubsearchappsubmission3.background

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SwitchPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    private val THEMEKEY = booleanPreferencesKey("theme_setting")
    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEMEKEY] ?: false
        }
    }
    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEMEKEY] = isDarkModeActive
        }
    }
    companion object {
        private var INSTANCE: com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences {
            return com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences.Companion.INSTANCE
                ?: synchronized(this) {
                val instance =
                    com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences(
                        dataStore
                    )
                INSTANCE = instance
                instance
            }
        }
    }

}