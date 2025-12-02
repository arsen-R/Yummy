package com.example.recipeapp.data.repository

import com.example.recipeapp.core.Result
import com.example.recipeapp.data.firebase.FirebaseAuthService
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuthService,
    //private val firestoreService: FirebaseFirestoreService
//    private val credentialRequest: GetCredentialRequest,
//    private val credentialManager: CredentialManager,
//    @ApplicationContext private val context: Context,
) : AuthRepository {

    override fun getCurrentUser(): User? {
        return firebaseAuth.getCurrentUser()
    }

    override fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return firebaseAuth.isCurrentUserAuthenticatedInFirebase()
    }

    override fun getAuthState(): Flow<Boolean> {
        return firebaseAuth.authStateFlow
    }

    override suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>> {
        return callbackFlow {
            trySend(Result.Loading)
            try {
                val user = firebaseAuth.signUpUserByEmailAndPassword(
                    email,
                    password
                )
                addUserToDatabase(onSuccess = {
                    trySend(Result.Success(user))
                }, onFailure = {
                    trySend(Result.Error(Exception("Firebase RealtimeDatabase is failed")))
                })
            }
            catch (e: IllegalArgumentException) {
                trySend(Result.Error(e))
            } catch (e: Exception) {
                trySend(Result.Error(e))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>> {
        return callbackFlow {
            trySend(Result.Loading)
            try {
                val user = firebaseAuth.signInUserByEmailAndPassword(
                    email,
                    password
                )
                trySend(Result.Success(user))
            }
//            catch (e: FirebaseAuthException) {
//                trySend(Result.Error(e))
//            } catch (e: FirebaseAuthEmailException) {
//                trySend(Result.Error(e))
//            } catch (e: FirebaseNetworkException) {
//                trySend(Result.Error(e))
//            } catch (e: FirebaseTooManyRequestsException) {
//                trySend(Result.Error(e))
//            }
            catch (e: IllegalArgumentException) {
                trySend(Result.Error(e))
            } catch (e: Exception) {
                trySend(Result.Error(e))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun singOutCurrentUser() {
        firebaseAuth.singOutCurrentUser()
    }

    override fun addUserToDatabase(onSuccess: () -> Unit, onFailure: () -> Unit) {
        getCurrentUser()?.apply {
//            firestoreService.addUserToDatabase(
//                user = getCurrentUser()!!,
//                onSuccess = onSuccess,
//                onFailure = onFailure
//            )
        }
    }

//    override fun signInUserByGoogleProvider(): Flow<Result<FirebaseUser>> {
//        return callbackFlow {
//            try {
//                Log.d(AuthRepositoryImpl::class.simpleName, "SignInWithGoogle: Loading")
//                trySend(Result.Loading)
//                val result = credentialManager.getCredential(context = context, credentialRequest)
//                val credential = result.credential
//                if (credential is CustomCredential) {
//                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
//                        try {
//                            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
//                            auth.signInWithCredential(firebaseCredential)
//                                .addOnCompleteListener { result ->
//                                    if (result.isSuccessful) {
//                                        if (result.result.additionalUserInfo?.isNewUser != true) {
//                                            trySend(Result.Error(Exception("User is exist")))
//                                            Log.d(
//                                                AuthRepositoryImpl::class.simpleName,
//                                                "signInWithGoogle: success"
//                                            )
//                                        }
//                                        addUserToDatabase(onSuccess = {
//                                            trySend(Result.Success(result.result.user))
//                                            Log.d(
//                                                AuthRepositoryImpl::class.simpleName,
//                                                "signInWithGoogle: success"
//                                            )
//                                        }, onFailure = {
//                                            trySend(Result.Error(Exception()))
//                                            Log.d(
//                                                AuthRepositoryImpl::class.simpleName,
//                                                "signInWithGoogle: success"
//                                            )
//                                        })
//
//                                    } else {
//                                        trySend(Result.Error(Exception()))
//                                        Log.e(
//                                            AuthRepositoryImpl::class.simpleName,
//                                            "signInWithGoogle: Failure"
//                                        )
//                                    }
//                                }
//                        } catch (e: Exception) {
//                            trySend(Result.Error(e))
//                            Log.e(
//                                AuthRepositoryImpl::class.simpleName,
//                                "signInWithGoogle: ${e.message}"
//                            )
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                trySend(Result.Error(e))
//                Log.e(
//                    AuthRepositoryImpl::class.simpleName,
//                    "signInWithGoogle: ${e.message}"
//                )
//            }
//            awaitClose()
//        }
//    }
}
