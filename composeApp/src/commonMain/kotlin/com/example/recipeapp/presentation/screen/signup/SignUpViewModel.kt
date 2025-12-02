package com.example.recipeapp.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.core.Result
import com.example.recipeapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel (
    private val repository: AuthRepository
) : ViewModel() {
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState get() = _signUpState.asStateFlow()

    private val _saveUserState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val saveUserState get() = _saveUserState.asStateFlow()

    fun fetchSignUpNewUserByEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            repository.signUpUserByEmailAndPassword(
                email = email, password = password
            ).distinctUntilChanged().collectLatest { result ->
                    _signUpState.update {
                        when (result) {
                            is Result.Loading -> {
                                SignUpState.Loading
                            }

                            is Result.Success -> {
                                SignUpState.Success
                            }

                            is Result.Error -> {
                                SignUpState.Error(result.exception)
                            }
                        }
                    }
                }
        }
    }
}

sealed interface SignUpState {
    object Idle : SignUpState
    object Loading : SignUpState
    object Success : SignUpState
    data class Error(val error: Throwable) : SignUpState
}