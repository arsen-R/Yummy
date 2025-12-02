package com.example.recipeapp.data.firebase

import cocoapods.FirebaseAuth.FIRAuth
import com.example.recipeapp.data.mapper.UserMapper
import com.example.recipeapp.domain.model.User
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class FirebaseAuthService(
    private val mapper: UserMapper
) {
    @OptIn(ExperimentalForeignApi::class)
    private val auth = FIRAuth.auth()

    @OptIn(ExperimentalForeignApi::class)
    actual fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser() != null
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String,
    ): User = suspendCoroutine { continuation ->
        auth.createUserWithEmail(email, password) { authResult, error ->
            when {
                error != null -> {
                    continuation.resumeWith(Result.failure(Exception(error.localizedDescription)))
                }

                authResult != null -> {
                    val firebaseUser = authResult.user()
                    if (firebaseUser != null) {
                        continuation.resume(
                            mapper.fromDomain(firebaseUser)
                        )
                    } else {
                        continuation.resumeWith(
                            Result.failure(Exception("User object is null"))
                        )
                    }
                }

                else -> {
                    continuation.resumeWith(
                        Result.failure(Exception("Unknown signup error"))
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun signInUserByEmailAndPassword(email: String, password: String): User =
        suspendCoroutine { continuation ->
            auth.signInWithEmail(email = email, password = password) { authResult, error ->
                when {
                    error != null -> {
                        continuation.resumeWith(
                            Result.failure(Exception(error.localizedDescription))
                        )
                    }

                    authResult != null -> {
                        val firebaseUser = authResult.user()
                        if (firebaseUser != null) {
                            continuation.resume(
                                mapper.fromDomain(firebaseUser)
                            )
                        } else {
                            continuation.resumeWith(
                                Result.failure(Exception("User is null after sign-in"))
                            )
                        }
                    }

                    else -> {
                        continuation.resumeWith(
                            Result.failure(Exception("Unknown sign-in error"))
                        )
                    }
                }
            }
        }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun singOutCurrentUser() {
        auth.signOut(null)
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun getCurrentUser(): User? {
        return mapper.fromDomain(auth.currentUser()!!)
    }


    @OptIn(ExperimentalForeignApi::class)
    actual val authStateFlow: Flow<Boolean>
        get() = callbackFlow {
            val listener = auth.addAuthStateDidChangeListener { _, user ->
                // true = logged in, false = logged out
                trySend(user != null)
            }
            awaitClose { auth.removeAuthStateDidChangeListener(listener) }
        }
}