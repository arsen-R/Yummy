package com.example.recipeapp.di

import com.example.recipeapp.data.database.DatabaseFactory
import com.example.recipeapp.data.firebase.FirebaseAuthService
import com.example.recipeapp.data.mapper.UserMapper
import com.example.recipeapp.data.storage.DatastoreFactory
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalForeignApi::class)
actual fun platformModule(): Module = module {
    single {
        DatastoreFactory().createDatastore()
    }
    single {
        DatabaseFactory().createDatabase()
    }
    single {
        UserMapper()
    }
    single {
        FirebaseAuthService(get())
    }
}