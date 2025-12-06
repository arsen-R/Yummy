package com.example.recipeapp

import android.app.Application
import com.example.recipeapp.di.initKoin
import com.google.firebase.Firebase
import com.google.firebase.initialize
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        Firebase.initialize(this)
        initKoin {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@Application)
        }
    }
}