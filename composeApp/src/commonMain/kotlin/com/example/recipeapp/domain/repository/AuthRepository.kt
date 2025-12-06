package com.example.recipeapp.domain.repository

import com.example.recipeapp.core.Result
import com.example.recipeapp.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun getCurrentUser(): User?

    fun isCurrentUserAuthenticatedInFirebase(): Boolean

    fun getAuthState(): Flow<Boolean>

    suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>>
    suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>>

    suspend fun singOutCurrentUser()

    fun addUserToDatabase(onSuccess: () -> Unit, onFailure: () -> Unit)

//    fun signInUserByGoogleProvider(): Flow<Result<FirebaseUser>>
}