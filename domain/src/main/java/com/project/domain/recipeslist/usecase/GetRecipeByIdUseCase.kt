package com.project.domain.recipeslist.usecase

import com.project.domain.recipeslist.model.Recipe

interface GetRecipeByIdUseCase {

    operator fun invoke(id: Int): Result<Recipe>
}