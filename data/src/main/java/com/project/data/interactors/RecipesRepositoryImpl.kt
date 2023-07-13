package com.project.data.interactors

import com.project.domain.recipeslist.RecipesRepository
import com.project.domain.recipeslist.model.Recipe

class RecipesRepositoryImpl : RecipesRepository {

    override fun getRecipes(): List<Recipe> {
        return dummyListOfRecipes
    }

    override fun editRecipe(newRecipe: Recipe) {
        val oldRecipe = dummyListOfRecipes.find { it.title == newRecipe.title }
        val index = dummyListOfRecipes.indexOf(oldRecipe)
        dummyListOfRecipes[index] = newRecipe
    }

    override fun getRecipeById(id: Int): Recipe {
        return dummyListOfRecipes.find {
            it.id == id
        } ?: dummyListOfRecipes[1]
    }

    companion object {
        private val dummyListOfRecipes = ArrayList<Recipe>(
            listOf(
                Recipe(
                    id = 1,
                    title = "Spaghetti Bolognese",
                    description = "A classic Italian pasta dish with ground beef and tomato sauce."
                ),
                Recipe(
                    id = 2,
                    title = "Roasted Chicken Thighs",
                    description = "Juicy and flavorful chicken thighs marinated in garlic and herbs."
                ),
                Recipe(
                    id = 3,
                    title = "Vegetable Stir Fry",
                    description = "A healthy and delicious stir fry with your favorite veggies and tofu."
                ),
                Recipe(
                    id = 4,
                    title = "Beef Stew",
                    description = "A hearty beef stew with vegetables and potatoes, perfect for cold nights."
                ),
                Recipe(
                    id = 5,
                    title = "Fish Tacos",
                    description = "Fresh and flavorful tacos with grilled fish, avocado, and salsa."
                ),
                Recipe(
                    id = 6,
                    title = "Pesto Pasta",
                    description = "A simple and delicious pasta dish with basil pesto and cherry tomatoes."
                ),
                Recipe(
                    id = 7,
                    title = "Sushi Rolls",
                    description = "Make your own sushi rolls at home with your favorite fillings."
                ),
                Recipe(
                    id = 8,
                    title = "Chicken Curry",
                    description = "A fragrant and spicy chicken curry with coconut milk and vegetables."
                ),
                Recipe(
                    id = 9,
                    title = "Quinoa Salad",
                    description = "A healthy and colorful salad with quinoa, vegetables, and a tangy dressing."
                ),
                Recipe(
                    id = 10,
                    title = "Shepherd's Pie",
                    description = "A classic comfort food dish with ground beef, mashed potatoes, and vegetables."
                )
            )
        )
    }
}