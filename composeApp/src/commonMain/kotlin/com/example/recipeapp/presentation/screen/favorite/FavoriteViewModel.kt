package com.example.recipeapp.presentation.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: RecipeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState())
    val uiState = _uiState.asStateFlow()
    private val _isRecipeSaved = MutableStateFlow(false)
    val isRecipeSaved = _isRecipeSaved.asStateFlow()


    init {
        getAllSavedRecipe()
    }

    private fun getAllSavedRecipe() {
        viewModelScope.launch {
            repository.getAllSavedRecipes().collectLatest { recipes ->
                _uiState.value = FavoriteUiState(
                    recipes = recipes,
                    savedIds = recipes.map { it.id }.toSet()
                )
            }
        }
    }

    fun toggleSave(recipe: Recipe) {
        viewModelScope.launch {
            isRecipeAdded(recipe.id!!)
            if (_isRecipeSaved.value) {
                repository.removeRecipe(recipe.id)
            } else {
                repository.insertRecipe(recipe)
            }
            //getAllSavedRecipe() // refresh state
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

    private fun isRecipeAdded(recipeId: Int) {
        viewModelScope.launch {
            repository.isRecipeSaved(recipeId).collectLatest {
                Napier.d(tag = "FavoriteViewModelLogger", message = "isRecipeAdded: $it",)
                _isRecipeSaved.value = it
            }
        }
    }

}

data class FavoriteUiState(
    val recipes: List<Recipe> = emptyList(),
    val savedIds: Set<Int?> = emptySet()
)