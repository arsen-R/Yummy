package com.example.recipeapp.presentation.screen.home

import androidx.compose.animation.fadeIn
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.DatastoreRepository
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: RecipeRepository,
    private val datastoreRepository: DatastoreRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<Recipe>> =
        MutableStateFlow(PagingData.empty())
    val uiState: StateFlow<PagingData<Recipe>> = _uiState.asStateFlow()
    private val _isRecipeSaved = MutableStateFlow(false)
    val isRecipeSaved = _isRecipeSaved.asStateFlow()

    init {
        fetchRecipeList()
    }

    fun fetchRecipeList() {
        viewModelScope.launch {
            getRecipeList()
        }
    }

    private suspend fun getRecipeList() {
        repository.getListRecipes()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest {
                _uiState.value = it
        }
    }
    fun toggleSave(recipe: Recipe) {
        viewModelScope.launch {
            datastoreRepository.getPreferenceSelection(stringPreferencesKey("USER_ID"))
                .firstOrNull().let { user ->
                    repository.toggleRecipe(user!!, recipe)
                }
        }
    }

    fun isRecipeAdded(recipeId: Int) {
        viewModelScope.launch {
            _isRecipeSaved.value = repository.isRecipeSaved(recipeId)
        }
    }
}