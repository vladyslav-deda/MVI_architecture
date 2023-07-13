package com.project.presentation.recipedetails.viewmodel

import com.project.domain.recipeslist.model.Recipe

data class RecipeDetailsState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false
)