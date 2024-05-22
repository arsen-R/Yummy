package com.example.recipeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<Recipe>> =
        MutableStateFlow(PagingData.empty())
    val uiState: StateFlow<PagingData<Recipe>> = _uiState.asStateFlow()

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