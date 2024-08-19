package com.example.recipeapp.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.recipeapp.data.mapper.toUser
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.repository.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
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
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    @Named("SIGN_IN_REQUEST")
    private var signInRequest: BeginSignInRequest,
    @Named("SIGN_UP_REQUEST")
    private var signUpRequest: BeginSignInRequest,
    private val database: FirebaseDatabase,
) : AuthRepository {

    private val currentUser = auth.currentUser

    override fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun oneTapSignInWithGoogle(): Flow<Resources<BeginSignInResult>> {
        return flow {
            try {
                emit(Resources.Loading())
                val signInResult = oneTapClient.beginSignIn(signInRequest).await()
                emit(Resources.Success(signInResult))
            } catch (e: Exception) {
                try {
                    val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                    emit(Resources.Success(signUpResult))
                } catch (e: Exception) {
                    emit(Resources.Error(e))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun signInUserByGoogle(
        googleCredential: AuthCredential
    ): Flow<Resources<Boolean>> {
        return flow {
            try {
                emit(Resources.Loading())
                val authResult = auth.signInWithCredential(googleCredential).await()
                val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
                if (isNewUser) {
                    addUserToRealTimeDatabase()
                    Log.d(AuthRepositoryImpl::class.simpleName, "Data is added!")
                }
                emit(Resources.Success(true))
            } catch (e: Exception) {
                emit(Resources.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { listener ->
            trySend(listener.currentUser == null)
            Log.i(AuthRepositoryImpl::class.simpleName, "User: ${listener.currentUser == (true ?: "Not authenticated")}")
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

    override suspend fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>> {
        return flow {
            emit(Resources.Loading())
            try {
                val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
                if (isNewUser) {
                    addUserToRealTimeDatabase()
                    Log.d(AuthRepositoryImpl::class.simpleName, "Data is added!")
                }
                emit(Resources.Success(authResult.user))
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

    override suspend fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resources<FirebaseUser>> {
        return flow {
            emit(Resources.Loading())
            try {
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

    private suspend fun addUserToRealTimeDatabase() {
        auth.currentUser?.apply {
            database.getReference("users")
                .child(uid)
                .setValue(toUser())
                .await()
        }
    }

    override fun singOutCurrentUser() {
        auth.signOut()
    }
}