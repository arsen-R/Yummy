package com.example.recipeapp

import androidx.datastore.preferences.core.intPreferencesKey
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
class MainActivityViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {
    private val _selectAppThemeKey = MutableStateFlow<Int?>(0)
    val selectAppThemeKey = _selectAppThemeKey.asStateFlow()
    private val supervisorJob = MutableStateFlow<Job?>(null)


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