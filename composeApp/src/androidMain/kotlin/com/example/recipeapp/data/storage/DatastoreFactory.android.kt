package com.example.recipeapp.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual class DatastoreFactory(private val context: Context) {
    actual fun createDatastore(): DataStore<Preferences> {
        return getDatastore(
            producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
        )
    }
}
