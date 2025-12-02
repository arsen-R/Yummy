package com.example.recipeapp.domain.mapper

interface BidirectionalMapper<T, R>: Mapper<T, R> {
    fun toDomain(value: R): T
}