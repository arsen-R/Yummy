package com.example.recipeapp.domain.mapper

interface Mapper<T, R> {
    fun fromDomain(value: T): R
}