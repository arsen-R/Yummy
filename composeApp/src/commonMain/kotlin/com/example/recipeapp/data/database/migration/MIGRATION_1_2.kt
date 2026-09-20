package com.example.recipeapp.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        super.migrate(connection)
        connection.execSQL("""
            CREATE TABLE users_table (
                userId TEXT NOT NULL PRIMARY KEY,
                email TEXT,
                createdDate INTEGER NOT NULL DEFAULT 0,
                provider TEXT NOT NULL
            )
        """)
        connection.execSQL("""
            CREATE TABLE IF NOT EXISTS favorite_recipe_ref (
                userId TEXT NOT NULL,
                recipeId INTEGER NOT NULL,
                PRIMARY KEY(userId, id)
            )
        """.trimIndent())
    }
}