package com.example.recipeapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.recipeapp.data.database.migration.MIGRATION_1_2
import com.example.recipeapp.domain.util.Constants
import kotlinx.coroutines.Dispatchers

actual class DatabaseFactory(private val context: Context) {
    actual fun createDatabase(): RecipeDatabase {
            val appContext = context.applicationContext
            val dbFile = appContext.getDatabasePath(Constants.DATABASE_NAME)

        return Room.databaseBuilder<RecipeDatabase>(
            context = appContext,
            klass = RecipeDatabase::class.java,
            name = dbFile.absolutePath
        ).setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}