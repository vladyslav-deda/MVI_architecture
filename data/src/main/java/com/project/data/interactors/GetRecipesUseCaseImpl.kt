package com.project.data.interactors

import com.project.domain.recipeslist.RecipesRepository
import com.project.domain.recipeslist.model.Recipe
import com.project.domain.recipeslist.usecase.GetRecipesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipesRepository
) : GetRecipesUseCase {

    override fun invoke(): Result<List<Recipe>> = runCatching {
        repository.getRecipes()
    }.fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(it) })
}