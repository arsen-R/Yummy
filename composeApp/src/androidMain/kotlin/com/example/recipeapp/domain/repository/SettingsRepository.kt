package com.example.recipeapp.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun<T> savePreferenceSelection(key: Preferences.Key<T>, selection: T)
    suspend fun <T> getPreferenceSelection(key: Preferences.Key<T>): Flow<T?>
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearAllPreference()
}