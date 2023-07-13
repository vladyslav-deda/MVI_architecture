package com.project.presentation.recipeslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.domain.recipeslist.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
        _recipesState.postValue(
            RecipesListState(
                isLoading = true
            )
        )
        val newRecipesState: RecipesListState = getRecipesUseCase.invoke()
            .fold(
                onSuccess = {
                    RecipesListState(
                        recipesList = it,
                        isLoading = false
                    )
                },
                onFailure = {
                    RecipesListState(
                        recipesList = null,
                        isLoading = false
                    )
                })

        _recipesState.postValue(newRecipesState)
    }
}