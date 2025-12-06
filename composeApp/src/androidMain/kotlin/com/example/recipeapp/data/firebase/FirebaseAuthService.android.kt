package com.example.recipeapp.data.firebase

import android.util.Log
import com.example.recipeapp.data.mapper.UserMapper
import com.example.recipeapp.data.repository.AuthRepositoryImpl
import com.example.recipeapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await

actual class FirebaseAuthService(
    private val auth: FirebaseAuth,
    private val mapper: UserMapper
) {
    actual fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    actual suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String,
    ): User {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
        if (authResult.additionalUserInfo?.isNewUser != true) {
            Log.i(AuthRepositoryImpl::class.simpleName, "User is exist")
            throw Exception("User is exist")
        }
        val user = authResult.user
        return mapper.fromDomain(user!!)
    }

    actual suspend fun signInUserByEmailAndPassword(email: String, password: String): User {
        val authResult = auth.signInWithEmailAndPassword(email, password).await()
        val user = authResult.user
        if (user == null) {
            Log.i(AuthRepositoryImpl::class.simpleName, "User not found")
            throw Exception("User not found")
        }
        return mapper.fromDomain(user)
    }

    actual suspend fun singOutCurrentUser() {
        auth.signOut()
    }

    actual fun getCurrentUser(): User? {
        return mapper.fromDomain(auth.currentUser!!)
    }

    actual fun getAuthState(viewModelScope: CoroutineScope): Flow<Boolean> {
        return callbackFlow {
            val authStateListener = FirebaseAuth.AuthStateListener { listener ->
                if (listener.currentUser != null) {
                    Log.i(AuthRepositoryImpl::class.simpleName, "User is logged!")
                }
                trySend(listener.currentUser == null)
            }
            auth.addAuthStateListener(authStateListener)
            awaitClose {
                auth.removeAuthStateListener(authStateListener)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = auth.currentUser == null
        )
    }
}