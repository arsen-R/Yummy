package com.example.recipeapp.data.util

sealed class Resources<out T> () {
    data object Loading: Resources<Nothing>()
    data class Success<T>(val data: T?): Resources<T>()
    data class Error(val exception: Throwable): Resources<Nothing>()
}