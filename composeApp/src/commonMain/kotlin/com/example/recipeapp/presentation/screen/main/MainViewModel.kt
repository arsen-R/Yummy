package com.example.recipeapp.presentation.screen.main

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repository.AuthRepository
import com.example.recipeapp.domain.repository.DatastoreRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class MainViewModel(
    private val repository: AuthRepository,
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {
    val isUserLogIn: StateFlow<FirebaseUser?> =
        repository.getAuthState().map { user ->
            datastoreRepository.savePreferenceSelection(stringPreferencesKey("USER_ID"), user?.uid ?: "")
            user
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = null
            )
}