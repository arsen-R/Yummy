package com.example.recipeapp.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _uiState: MutableStateFlow<PagingData<Recipe>> =
        MutableStateFlow(PagingData.empty())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun onSearch(query: String) {
        if (query.isNotBlank()) {
            job?.cancel()
            job = viewModelScope.launch {
                delay(2000)
                fetchSearchRecipesList(query)
            }
        }
    }

    private suspend fun fetchSearchRecipesList(query: String) {
        repository.searchRecipes(query)
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

    fun getSearchText(query: String) {
        _searchText.value = query
    }
}