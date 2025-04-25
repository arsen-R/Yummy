package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.util.Resources
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun isCurrentUserAuthenticatedInFirebase(): Boolean

    suspend fun oneTapSignInWithGoogle(): Flow<Resources<BeginSignInResult>>

    suspend fun signInUserByGoogle(googleCredential: AuthCredential): Flow<Resources<Boolean>>

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
}