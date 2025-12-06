package com.example.recipeapp.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.recipeapp.domain.util.Constants
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
    @OptIn(ExperimentalForeignApi::class)
    actual fun createDatabase(): RecipeDatabase {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )

        val dbFilePath = requireNotNull(documentDirectory?.path) + "/${Constants.DATABASE_NAME}"
        return Room.databaseBuilder<RecipeDatabase>(
            name = dbFilePath,
        ).setQueryCoroutineContext(Dispatchers.IO).fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver()).build()
    }
}