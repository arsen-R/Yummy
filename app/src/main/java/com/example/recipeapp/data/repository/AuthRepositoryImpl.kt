package com.example.recipeapp.data.repository

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.recipeapp.data.mapper.toUser
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.repository.AuthRepository
import com.example.recipeapp.domain.util.Constants
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
    private val credentialRequest: GetCredentialRequest,
    private val credentialManager: CredentialManager,
    @ApplicationContext private val context: Context,
) : AuthRepository {

    private val currentUser = auth.currentUser

    override fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
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
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        auth.currentUser == null
    )

    override fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>> {
        return callbackFlow {
            trySend(Resources.Loading)
            try {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (task.result.additionalUserInfo?.isNewUser == true) {
                            addUserToDatabase(onSuccess = {
                                trySend(Resources.Success(task.result.user))
                            }, onFailure = {
                                trySend(Resources.Error(Exception("Firebase RealtimeDatabase is failed")))
                            })
                        } else {
                            trySend(Resources.Error(Exception("This is exist")))
                        }
                    }
                }
            } catch (e: FirebaseAuthException) {
                trySend(Resources.Error(e))
            } catch (e: FirebaseAuthEmailException) {
                trySend(Resources.Error(e))
            } catch (e: FirebaseNetworkException) {
                trySend(Resources.Error(e))
            } catch (e: FirebaseTooManyRequestsException) {
                trySend(Resources.Error(e))
            } catch (e: IllegalArgumentException) {
                trySend(Resources.Error(e))
            } catch (e: Exception) {
                trySend(Resources.Error(e))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>> {
        return flow {
            emit(Resources.Loading)
            try {
                Log.d(AuthRepositoryImpl::class.simpleName, "")
                val results = auth.signInWithEmailAndPassword(email, password).await()
                emit(Resources.Success(results.user))
            } catch (e: FirebaseAuthException) {
                emit(Resources.Error(e))
            } catch (e: FirebaseAuthEmailException) {
                emit(Resources.Error(e))
            } catch (e: FirebaseNetworkException) {
                emit(Resources.Error(e))
            } catch (e: FirebaseTooManyRequestsException) {
                emit(Resources.Error(e))
            } catch (e: IllegalArgumentException) {
                emit(Resources.Error(e))
            } catch (e: Exception) {
                emit(Resources.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun addUserToRealTimeDatabase(onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.currentUser?.apply {
            database.getReference("users")
                .child(uid)
                .setValue(toUser()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    }
                }.addOnFailureListener {
                    onFailure()
                }
        }
    }

    override fun singOutCurrentUser() {
        auth.signOut()
    }

    override fun addUserToDatabase(onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.currentUser?.apply {
            firestore.collection(Constants.USERS_COLLECTION_PATH)
                .document(uid)
                .set(toUser())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                        Log.i(
                            AuthRepositoryImpl::class.simpleName,
                            "User added to Firestore database"
                        )
                    }
                }
                .addOnFailureListener {
                    onFailure()
                }
        }
    }

    override fun signInUserByGoogleProvider(): Flow<Resources<FirebaseUser>> {
        return callbackFlow {
            try {
                Log.d(AuthRepositoryImpl::class.simpleName, "SignInWithGoogle: Loading")
                trySend(Resources.Loading)
                val result = credentialManager.getCredential(context = context, credentialRequest)
                val credential = result.credential
                if (credential is CustomCredential) {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener { result ->
                                    if (result.isSuccessful) {
                                        if (result.result.additionalUserInfo?.isNewUser != true) {
                                            trySend(Resources.Error(Exception("User is exist")))
                                            Log.d(
                                                AuthRepositoryImpl::class.simpleName,
                                                "signInWithGoogle: success"
                                            )
                                        }
                                        addUserToDatabase(onSuccess = {
                                            trySend(Resources.Success(result.result.user))
                                            Log.d(
                                                AuthRepositoryImpl::class.simpleName,
                                                "signInWithGoogle: success"
                                            )
                                        }, onFailure = {
                                            trySend(Resources.Error(Exception()))
                                            Log.d(
                                                AuthRepositoryImpl::class.simpleName,
                                                "signInWithGoogle: success"
                                            )
                                        })

                                    } else {
                                        trySend(Resources.Error(Exception()))
                                        Log.e(
                                            AuthRepositoryImpl::class.simpleName,
                                            "signInWithGoogle: Failure"
                                        )
                                    }
                                }
                        } catch (e: Exception) {
                            trySend(Resources.Error(e))
                            Log.e(
                                AuthRepositoryImpl::class.simpleName,
                                "signInWithGoogle: ${e.message}"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                trySend(Resources.Error(e))
                Log.e(
                    AuthRepositoryImpl::class.simpleName,
                    "signInWithGoogle: ${e.message}"
                )
            }
            awaitClose()
        }
    }
}