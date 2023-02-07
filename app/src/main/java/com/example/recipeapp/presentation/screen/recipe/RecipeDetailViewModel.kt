package com.example.recipeapp.presentation.screen.recipe

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RecipeDetailRepositoryImpl
import com.example.recipeapp.data.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepositoryImpl
) : ViewModel() {

    private val _uiState: MutableStateFlow<RecipeDetailUiState> =
        MutableStateFlow(RecipeDetailUiState())
    val uiState: StateFlow<RecipeDetailUiState> get() = _uiState.asStateFlow()

    fun fetchRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            try {
                recipeDetailRepository.getRecipeDetail(recipeId).collectLatest { response ->
                    when (response) {
                        is Resources.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true,
                                recipe = null,
                                throwable = null
                            )
                        }
                        is Resources.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                recipe = response.data,
                                throwable = null
                            )
                        }
                        is Resources.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                recipe = null,
                                throwable = response.exception
                            )
                        }
                    }
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    recipe = null,
                    throwable = exception
                )
            }
        }
    }
}