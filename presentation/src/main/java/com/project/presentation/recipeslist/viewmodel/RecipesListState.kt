package com.project.presentation.recipeslist.viewmodel

import com.project.domain.recipeslist.model.Recipe

data class RecipesListState(
    val recipesList: List<Recipe>? = null,
    val isLoading: Boolean = false
)