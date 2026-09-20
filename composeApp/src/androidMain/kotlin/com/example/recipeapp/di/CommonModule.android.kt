package com.example.recipeapp.di

import com.example.recipeapp.data.database.DatabaseFactory
import com.example.recipeapp.data.storage.DatastoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single {
        DatastoreFactory(get()).createDatastore()
    }
    single {
        DatabaseFactory(get()).createDatabase()
    }
}