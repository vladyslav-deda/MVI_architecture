package com.project.domain.recipeslist.usecase

import com.project.domain.recipeslist.model.Recipe

interface GetRecipesUseCase {

    operator fun invoke(): Result<List<Recipe>>
}