package com.example.recipeapp.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import okio.Path.Companion.toPath

internal const val dataStoreFileName = "user_preferences.preferences_pb"

expect class DatastoreFactory {
    fun createDatastore(): DataStore<Preferences>
}

fun getDatastore(producePath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { producePath().toPath() }
    )
}