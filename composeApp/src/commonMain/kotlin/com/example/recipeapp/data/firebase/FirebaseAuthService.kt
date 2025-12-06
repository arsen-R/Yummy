package com.example.recipeapp.data.firebase

import com.example.recipeapp.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

expect class FirebaseAuthService {
    fun getCurrentUser(): User?

    fun isCurrentUserAuthenticatedInFirebase(): Boolean

    fun getAuthState(viewModelScope: CoroutineScope): Flow<Boolean>
    suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String,
    ): User

    suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): User

    suspend fun singOutCurrentUser()

    //suspend fun signInUserByGoogleProvider(): User
}