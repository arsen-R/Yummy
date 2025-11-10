
package com.example.recipeapp.presentation.screen.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.repository.AuthRepository
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
) : ViewModel() {
    private val _signInWithGoogleState =
        MutableStateFlow<SignInWithGoogleState>(SignInWithGoogleState.Idle)
    val signInWithGoogle get() = _signInWithGoogleState.asStateFlow()

    fun isCurrentUserAuthenticated(): Boolean {
        return repository.isCurrentUserAuthenticatedInFirebase()
    }

    fun signInWithGoogle() {
        viewModelScope.launch {
            repository.signInUserByGoogleProvider()
                .distinctUntilChanged()
                .collectLatest { response ->
                    _signInWithGoogleState.update {
                        when(response) {
                            is Resources.Loading -> {
                                SignInWithGoogleState.Loading
                            }
                            is Resources.Success -> {
                                SignInWithGoogleState.Success
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

sealed interface SignInWithGoogleState {
    data object Idle: SignInWithGoogleState
    data object Loading: SignInWithGoogleState
    data object Success: SignInWithGoogleState
    data class Error(val errorMessage: String): SignInWithGoogleState
}