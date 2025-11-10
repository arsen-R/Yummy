package com.example.recipeapp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules()
    }
}