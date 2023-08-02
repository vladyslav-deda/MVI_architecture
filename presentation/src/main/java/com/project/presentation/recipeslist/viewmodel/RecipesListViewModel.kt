package com.project.presentation.recipeslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.domain.recipeslist.model.Recipe
import com.project.domain.recipeslist.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipesState = MutableLiveData<RecipesListState>()
    val recipesState: LiveData<RecipesListState> = _recipesState

    fun handleEvent(event: RecipesListEvent) {
        when (event) {
            RecipesListEvent.LoadRecipes -> loadRecipes()
        }
    }

    private fun loadRecipes() {
        updateState(isLoading = true)
        var recipesList: List<Recipe>? = null
        getRecipesUseCase.invoke()
            .fold(
                onSuccess = {
                    recipesList = it
                },
                onFailure = {
                    Timber.e(it)
                }
            )
        updateState(recipesList = recipesList, isLoading = false)
    }

    private fun updateState(recipesList: List<Recipe>? = null, isLoading: Boolean) {
        _recipesState.postValue(
            RecipesListState(
                recipesList = recipesList,
                isLoading = isLoading
            )
        )
    }
}