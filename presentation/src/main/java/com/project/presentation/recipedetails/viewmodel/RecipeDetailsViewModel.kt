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
        updateState(isLoading = true)
        val recipe = getRecipeByIdUseCase.invoke(recipeId)
            .fold(
                onSuccess = { it },
                onFailure = { null }
            )
        updateState(recipe = recipe, isLoading = false)
    }

    private fun updateRecipe(newRecipeDescription: String) {
        _recipeState.value?.recipe?.let {
            updateState(isLoading = true)
            val newRecipe = Recipe(
                id = it.id,
                title = it.title,
                description = newRecipeDescription
            )
            val recipe = editRecipeUseCase.invoke(newRecipe)
                .fold(
                    onSuccess = { newRecipe },
                    onFailure = { null }
                )
            updateState(recipe = recipe, isLoading = false)
        }
    }

    private fun updateState(recipe: Recipe? = null, isLoading: Boolean) {
        _recipeState.postValue(
            RecipeDetailsState(recipe = recipe, isLoading = isLoading)
        )
    }
}