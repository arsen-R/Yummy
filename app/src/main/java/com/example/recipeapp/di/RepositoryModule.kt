package com.example.recipeapp.di

import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun recipeRepository(repositoryImpl: RecipeRepositoryImpl): RecipeRepository
}