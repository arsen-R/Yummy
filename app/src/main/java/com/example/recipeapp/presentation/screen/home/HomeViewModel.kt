package com.example.recipeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.HomeRepositoryImpl
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchRecipeList()
    }

    fun fetchRecipeList() {
        viewModelScope.launch {
            try {
                homeRepository.getListRecipe().collectLatest { response ->
                    when (response) {
                        is Resources.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true,
                                recipe = emptyList(),
                                error = null
                            )
                        }
                        is Resources.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                recipe = response.data?.results,
                                error = null
                            )
                        }
                        is Resources.Error -> {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    recipe = null,
                                    error = response.exception
                                )
                        }
                    }
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = exception)
            }
        }
    }

    data class HomeUiState(
        val isLoading: Boolean = false,
        val recipe: List<RecipeResult?>? = null,
        val error: Throwable? = null
    )
}