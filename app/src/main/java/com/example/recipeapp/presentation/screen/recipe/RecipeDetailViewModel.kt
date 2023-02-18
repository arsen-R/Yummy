package com.example.recipeapp.presentation.screen.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: RecipeRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState: StateFlow<RecipeDetailUiState> = _uiState.asStateFlow()

    init {
        fetchRecipeDetails()
    }

    private fun getRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            try {
                repository.getRecipeDetail(recipeId).collectLatest { response ->
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
    private fun getSimilarRecipe(recipeId: Int) {
        viewModelScope.launch {
            repository.getSimilarRecipes(recipeId).collectLatest { response ->
                try {
                    when(response) {
                        is Resources.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = true,
                                similarRecipe = null,
                                throwable = null
                            )
                        }
                        is Resources.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                similarRecipe = response.data,
                                throwable = null
                            )
                        }
                        is Resources.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                similarRecipe = null,
                                throwable = response.exception
                            )
                        }
                    }
                } catch (exception: Exception) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        similarRecipe = null,
                        throwable = exception
                    )
                }
            }
        }
    }
    fun fetchRecipeDetails() {
        val recipeId: Int = savedStateHandle[Constants.DETAIL_ARGUMENT_KEY]!!
        getRecipeDetails(recipeId)
        getSimilarRecipe(recipeId)
    }

    fun insertRecipe(recipe: RecipeResult) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun removeRecipe(recipeId: Int) {
        viewModelScope.launch {
            repository.removeRecipe(recipeId)
        }
    }

    fun isRecipeAdded(recipeId: Int) = repository.isRecipeSaved(recipeId)
}