package com.example.recipeapp.presentation.screen.account_management

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repository.SettingsRepository
import com.example.recipeapp.domain.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountManagementViewModel @Inject constructor(
    private val repository: SettingsRepository,
) : ViewModel() {
    private val _selectAppThemeKey = MutableStateFlow<Int?>(0)
    val selectAppThemeKey = _selectAppThemeKey.asStateFlow()
    private val supervisorJob = MutableStateFlow<Job?>(null)


    fun saveAppThemeSelection(selection: Int) {
        viewModelScope.launch {
            repository.savePreferenceSelection(
                intPreferencesKey(Constants.APP_THEME_PREFERENCES),
                selection
            )
        }
    }


    init {
        getAppThemeSelection()
    }

    private fun getAppThemeSelection() {
        val job = viewModelScope.launch {
            repository.getPreferenceSelection(intPreferencesKey(Constants.APP_THEME_PREFERENCES))
                .collectLatest {
                    _selectAppThemeKey.value = it
                }
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}