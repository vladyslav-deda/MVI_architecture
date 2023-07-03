package com.project.presentation.recipedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.domain.recipeslist.model.Recipe
import com.project.domain.recipeslist.usecase.EditRecipeUseCase
import com.project.domain.recipeslist.usecase.GetRecipeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val editRecipeUseCase: EditRecipeUseCase
) : ViewModel() {

    private val _recipeState = MutableLiveData<RecipeDetailsState>()
    val recipeState: LiveData<RecipeDetailsState> = _recipeState

    fun handleEvent(event: RecipeDetailsEvent) {
        when (event) {
            is RecipeDetailsEvent.LoadRecipe -> loadRecipeById(event.recipeId)
            is RecipeDetailsEvent.UpdateRecipeDescription -> updateRecipe(event.newRecipeDescription)
        }
    }

    private fun loadRecipeById(recipeId: Int) {
        _recipeState.postValue(
            RecipeDetailsState(
                isLoading = true
            )
        )
        getRecipeByIdUseCase.invoke(recipeId)
            .fold(
                onSuccess = {
                    _recipeState.postValue(
                        RecipeDetailsState(
                            recipe = it,
                            isLoading = false
                        )
                    )
                },
                onFailure = {
                    _recipeState.postValue(
                        RecipeDetailsState(
                            recipe = null,
                            isLoading = false
                        )
                    )
                })
    }

    private fun updateRecipe(newRecipeDescription: String) {
        _recipeState.value?.recipe?.let {
            _recipeState.postValue(
                RecipeDetailsState(
                    isLoading = true
                )
            )
            val newRecipe = Recipe(
                id = it.id,
                title = it.title,
                description = newRecipeDescription
            )
            val newRecipeState: RecipeDetailsState = editRecipeUseCase.invoke(newRecipe)
                .fold(
                    onSuccess = {
                        RecipeDetailsState(
                            recipe = newRecipe,
                            isLoading = false
                        )
                    },
                    onFailure = {
                        RecipeDetailsState(
                            recipe = null,
                            isLoading = false
                        )
                    }
                )

            _recipeState.postValue(newRecipeState)
        }
    }
}