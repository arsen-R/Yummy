package com.example.recipeapp.presentation.screen.favorite

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.DatastoreRepository
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: RecipeRepository,
    private val datastoreRepository: DatastoreRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState())
    val uiState = _uiState.asStateFlow()
    private val _isRecipeSaved = MutableStateFlow(false)
    val isRecipeSaved = _isRecipeSaved.asStateFlow()

    init {
        getAllSavedRecipe()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getAllSavedRecipe() {
        viewModelScope.launch {
            datastoreRepository.getPreferenceSelection(stringPreferencesKey("USER_ID"))
                .flatMapLatest { userId ->
                    repository.getAllSavedRecipes(userId!!).filterNotNull()
                }.collectLatest { recipes ->
                    _uiState.value = FavoriteUiState(
                        recipes = recipes,
                        savedIds = recipes.map { it.id }.toSet()
                    )
                }
        }
    }

    fun toggleSave(recipe: Recipe) {
        viewModelScope.launch {
            val userId = datastoreRepository
                .getPreferenceSelection(stringPreferencesKey("USER_ID"))
                .firstOrNull()
                ?: return@launch
            repository.toggleRecipe(userId, recipe)
        }
    }

    private fun isRecipeAdded(recipeId: Int) {
        viewModelScope.launch {
            _isRecipeSaved.value = repository.isRecipeSaved(recipeId)
        }
    }

}

data class FavoriteUiState(
    val recipes: List<Recipe> = emptyList(),
    val savedIds: Set<Int?> = emptySet()
)