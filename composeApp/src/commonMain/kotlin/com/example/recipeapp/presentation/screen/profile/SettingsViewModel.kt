package com.example.recipeapp.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repository.AuthRepository
import kotlinx.coroutines.launch


class SettingsViewModel (
    private val repository: AuthRepository
): ViewModel() {

    fun fetchSignOutCurrentUser() {
        viewModelScope.launch {
            repository.singOutCurrentUser()
        }
    }
}