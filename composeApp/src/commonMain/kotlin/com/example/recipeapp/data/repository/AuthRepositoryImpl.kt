package com.example.recipeapp.data.repository

import com.example.recipeapp.core.Result
import com.example.recipeapp.data.mapper.UserMapper
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.repository.AuthRepository
import com.example.recipeapp.domain.util.Constants
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(
    private val auth: FirebaseAuth = Firebase.auth,
    private val firestore: FirebaseFirestore = Firebase.firestore,
    private val userMapper: UserMapper
) : AuthRepository {

    override fun getCurrentUser(): User {
        return userMapper.fromDomain(auth.currentUser)
    }

    override fun isCurrentUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getAuthState(): Flow<FirebaseUser?> {
        return auth.authStateChanged
    }

    override fun signUpUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>> {
        return callbackFlow {
            trySend(Result.Loading)
            try {
                val result = auth.createUserWithEmailAndPassword(email, password)
                if (result.additionalUserInfo?.isNewUser == true) {
                    val user = userMapper.fromDomain(result.user)
                    addUserToDatabase(user)
                    Napier.d(tag = AuthRepositoryImpl::class.simpleName) { "signUpUserByEmailAndPassword: User added to Firestore database" }
                    trySend(Result.Success(user))
                } else {
                    Napier.e(tag = AuthRepositoryImpl::class.simpleName) { "signUpUserByEmailAndPassword: User not added to Firestore database" }
                    trySend(Result.Error(Exception("Firebase RealtimeDatabase is failed")))
                }
            } catch (e: FirebaseAuthException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseAuthEmailException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseNetworkException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseTooManyRequestsException) {
                trySend(Result.Error(e))
            } catch (e: IllegalArgumentException) {
                trySend(Result.Error(e))
            } catch (e: Exception) {
                trySend(Result.Error(e))
            } catch (e: IllegalArgumentException) {
                trySend(Result.Error(e))
            } catch (e: Exception) {
                trySend(Result.Error(e))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override fun signInUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<Result<User>> {
        return callbackFlow {
            trySend(Result.Loading)
            try {
                val user = auth.signInWithEmailAndPassword(
                    email,
                    password
                )
                trySend(Result.Success(userMapper.fromDomain(user.user)))
            } catch (e: FirebaseAuthException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseAuthEmailException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseNetworkException) {
                trySend(Result.Error(e))
            } catch (e: FirebaseTooManyRequestsException) {
                trySend(Result.Error(e))
            } catch (e: IllegalArgumentException) {
                trySend(Result.Error(e))
            } catch (e: Exception) {
                trySend(Result.Error(e))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun singOutCurrentUser() {
        auth.signOut()
    }

    override suspend fun addUserToDatabase(user: User) {
        firestore.collection(Constants.USERS_COLLECTION_PATH)
            .document(user.uid!!)
            .set(user)
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
