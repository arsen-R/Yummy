package com.example.recipeapp.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("""
            ALTER TABLE `FavoriteRecipeEntityRef` RENAME TO `UserRecipeCrossRef`;
        """.trimIndent())
        connection.execSQL("""
            CREATE TABLE IF NOT EXISTS `new_user_table` (
              `userId` TEXT NOT NULL,
              `provider` TEXT NOT NULL,
              `email` TEXT NOT NULL,
              PRIMARY KEY(`userId`)
            )
        """)
        connection.execSQL("DROP TABLE user_table")
        connection.execSQL("ALTER TABLE user_table_new RENAME TO user_table")
    }
}