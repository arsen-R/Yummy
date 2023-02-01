package com.example.recipeapp.data.util

sealed class Resources<out T> () {
    class Loading: Resources<Nothing>()
    class Success<T>(val data: T?): Resources<T>()
    class Error(val exception: Throwable): Resources<Nothing>()
}