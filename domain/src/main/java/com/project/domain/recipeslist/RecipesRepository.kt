package com.project.domain.recipeslist

import com.project.domain.recipeslist.model.Recipe

interface RecipesRepository {

    fun getRecipes(): List<Recipe>

    fun editRecipe(newRecipe: Recipe)

    fun getRecipeById(id: Int): Recipe
}