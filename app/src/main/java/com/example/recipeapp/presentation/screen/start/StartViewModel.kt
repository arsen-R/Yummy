
package com.example.recipeapp.presentation.screen.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.repository.AuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {
    private val _onTapSignInState =
        MutableStateFlow<OnTapSignInResultState>(OnTapSignInResultState.Idle)
    val onTapSignInResponse get() = _onTapSignInState.asStateFlow()

    private val _signInWithGoogleState =
        MutableStateFlow<SignInWithGoogleState>(SignInWithGoogleState.Idle)
    val signInWithGoogle get() = _signInWithGoogleState.asStateFlow()

    fun isCurrentUserAuthenticated(): Boolean {
        return repository.isCurrentUserAuthenticatedInFirebase()
    }

    fun onTapSignInResponse() {
        viewModelScope.launch {
            repository.oneTapSignInWithGoogle()
                .distinctUntilChanged()
                .collectLatest { response ->
                    _onTapSignInState.update {
                        when (response) {
                            is Resources.Loading -> {
                                OnTapSignInResultState.Loading
                            }

                            is Resources.Success -> {
                                OnTapSignInResultState.Success(response.data)
                            }

                            is Resources.Error -> {
                                OnTapSignInResultState.Error(response.exception.message.toString())
                            }
                        }
                    }
                }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) {
        viewModelScope.launch {
            repository.signInUserByGoogle(googleCredential)
                .distinctUntilChanged()
                .collectLatest { response ->
                    _signInWithGoogleState.update {
                        when(response) {
                            is Resources.Loading -> {
                                SignInWithGoogleState.Loading
                            }
                            is Resources.Success -> {
                                SignInWithGoogleState.Success(response.data)
                            }
                            is Resources.Error -> {
                                SignInWithGoogleState.Error(response.exception.message.toString())
                            }
                        }
                    }
                }
        }
    }
}

sealed interface OnTapSignInResultState {
    object Idle : OnTapSignInResultState
    object Loading : OnTapSignInResultState
    data class Success(val beginSignInResult: BeginSignInResult?) : OnTapSignInResultState
    data class Error(val errorMessage: String) : OnTapSignInResultState
}

sealed interface SignInWithGoogleState {
    object Idle: SignInWithGoogleState
    object Loading: SignInWithGoogleState
    data class Success(val isUserSignIn: Boolean?): SignInWithGoogleState
    data class Error(val errorMessage: String): SignInWithGoogleState
}