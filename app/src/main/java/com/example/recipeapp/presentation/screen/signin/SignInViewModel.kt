package com.example.recipeapp.presentation.screen.signin

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
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState get() = _signInState.asStateFlow()

    fun fetchSignIn(email: String, password: String) {
        viewModelScope.launch {
            repository.signInUserByEmailAndPassword(
                email = email,
                password = password
            ).distinctUntilChanged()
                .collectLatest { result ->
                    _signInState.update {
                        when (result) {
                            is Resources.Loading -> {
                                SignInState.Loading
                            }

                            is Resources.Success -> {
                                SignInState.Success
                            }

                            is Resources.Error -> {
                                SignInState.Error(result.exception)
                            }
                        }
                    }
                }
        }
    }
}

sealed interface SignInState {
    object Idle : SignInState
    object Loading : SignInState
    object Success : SignInState
    data class Error(val error: Throwable) : SignInState
}