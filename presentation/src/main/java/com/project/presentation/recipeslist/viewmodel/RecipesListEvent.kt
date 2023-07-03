package com.project.presentation.recipeslist.viewmodel

sealed class RecipesListEvent {
    object LoadRecipes : RecipesListEvent()
}
