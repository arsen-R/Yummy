package com.example.recipeapp.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: AuthRepository
) : ViewModel() {
    private val _isUserLogIn = MutableStateFlow(false)
    val isUserLogIn = _isUserLogIn.asStateFlow()

    init {
        getIsLoginUser()
    }

    private fun getIsLoginUser() {
        viewModelScope.launch {
            repository.getAuthState().collectLatest {
                _isUserLogIn.value = it
            }
        }
    }
}