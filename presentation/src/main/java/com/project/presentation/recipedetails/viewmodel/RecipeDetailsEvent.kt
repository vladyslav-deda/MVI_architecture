package com.project.presentation.recipedetails.viewmodel


sealed class RecipeDetailsEvent {
    class LoadRecipe(val recipeId: Int) : RecipeDetailsEvent()
    class UpdateRecipeDescription(val newRecipeDescription: String) : RecipeDetailsEvent()
}