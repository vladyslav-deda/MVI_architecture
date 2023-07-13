package com.project.data.interactors

import com.project.domain.recipeslist.RecipesRepository
import com.project.domain.recipeslist.model.Recipe
import com.project.domain.recipeslist.usecase.GetRecipeByIdUseCase
import javax.inject.Inject

class GetRecipeByIdUseCaseImpl @Inject constructor(
    private val repository: RecipesRepository
) : GetRecipeByIdUseCase {

    override fun invoke(id: Int): Result<Recipe> = runCatching {
        repository.getRecipeById(id)
    }.fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(it) })
}