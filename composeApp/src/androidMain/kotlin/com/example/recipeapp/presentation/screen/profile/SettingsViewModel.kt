package com.example.recipeapp.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    fun fetchSignOutCurrentUser() {
        viewModelScope.launch {
            repository.singOutCurrentUser()
        }
    }
}