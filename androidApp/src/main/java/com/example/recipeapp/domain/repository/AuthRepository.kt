package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.util.Resources
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun isCurrentUserAuthenticatedInFirebase(): Boolean

    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>

    fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>>

    suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>>

    fun singOutCurrentUser()

    fun addUserToDatabase(onSuccess: () -> Unit, onFailure: () -> Unit)

    fun signInUserByGoogleProvider(): Flow<Resources<FirebaseUser>>
}