package com.example.recipeapp.presentation.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<List<Recipe>>(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        getAllSavedRecipe()
    }

    private fun getAllSavedRecipe() {
        viewModelScope.launch {
            repository.getAllSavedRecipes().collectLatest { recipe ->
                _uiState.value = recipe
            }
        }
    }

    fun insertRecipe(recipe: Recipe) {
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