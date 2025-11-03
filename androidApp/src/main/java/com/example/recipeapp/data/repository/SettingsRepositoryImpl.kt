package com.example.recipeapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recipeapp.domain.repository.SettingsRepository
import com.example.recipeapp.domain.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
) : SettingsRepository {
    override suspend fun<T> savePreferenceSelection(key: Preferences.Key<T>, selection: T) {
        datastore.edit { preference ->
            preference[key] = selection
        }
    }

    override suspend fun <T> getPreferenceSelection(key: Preferences.Key<T>): Flow<T?> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            preference[key]
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        datastore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun <T> clearAllPreference() {
        datastore.edit { preferences ->
            preferences.clear()
        }
    }
}