package com.project.domain.recipeslist.usecase

import com.project.domain.recipeslist.model.Recipe

interface EditRecipeUseCase {

    operator fun invoke(newRecipe: Recipe): Result<Unit>
}