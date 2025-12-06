package com.example.recipeapp.di

import com.example.recipeapp.data.database.DatabaseFactory
import com.example.recipeapp.data.firebase.FirebaseAuthService
import com.example.recipeapp.data.mapper.UserMapper
import com.example.recipeapp.data.storage.DatastoreFactory
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single {
        DatastoreFactory(get()).createDatastore()
    }
    single {
        DatabaseFactory(get()).createDatabase()
    }
    single {
        FirebaseAuthService(get(), get())
    }
//    single {
//        FirebaseFirestoreService(get())
//    }
    single {
        Firebase.auth
    }
    single {
        Firebase.firestore
    }
    single {
        UserMapper()
    }
}