package com.project.data.interactors

import com.project.domain.recipeslist.RecipesRepository
import com.project.domain.recipeslist.model.Recipe
import com.project.domain.recipeslist.usecase.EditRecipeUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EditRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipesRepository
) : EditRecipeUseCase {

    override fun invoke(newRecipe: Recipe): Result<Unit> = runCatching {
        repository.editRecipe(newRecipe)
    }
}