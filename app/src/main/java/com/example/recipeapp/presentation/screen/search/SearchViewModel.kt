package com.example.recipeapp.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecipeRepositoryImpl,
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun onSearch(query: String) {
        if (query.isNotBlank()) {
            job?.cancel()
            job = viewModelScope.launch {
                delay(2000)
                repository.searchRecipe(query).collectLatest { response ->
                    try {
                        when (response) {
                            is Resources.Loading -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = true,
                                    result = emptyList(),
                                    throwable = null
                                )
                            }
                            is Resources.Success -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = false,
                                    result = response.data?.results ?: emptyList(),
                                    throwable = null
                                )
                            }
                            is Resources.Error -> {
                                _uiState.value = _uiState.value.copy(
                                    isLoading = false,
                                    result = emptyList(),
                                    throwable = response.exception
                                )
                            }
                        }
                    } catch (exception: Exception) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            result = emptyList(),
                            throwable = exception
                        )
                    }
                }
            }
        }
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

    fun getSearchText(query: String) {
        _searchText.value = query
    }
}