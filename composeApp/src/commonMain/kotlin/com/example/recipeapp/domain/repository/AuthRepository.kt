package com.example.recipeapp.domain.repository

import com.example.recipeapp.core.Result
import com.example.recipeapp.domain.model.User
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun getCurrentUser(): User?

    fun isCurrentUserAuthenticatedInFirebase(): Boolean

    //fun getAuthState(): Flow<Boolean>
    fun getAuthState(): Flow<FirebaseUser?>

     fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>>
     fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>>

    suspend fun singOutCurrentUser()

    suspend fun addUserToDatabase(user: User)

//    fun signInUserByGoogleProvider(): Flow<Result<FirebaseUser>>
}