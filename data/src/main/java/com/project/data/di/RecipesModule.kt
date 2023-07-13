package com.project.data.di

import com.project.data.interactors.EditRecipeUseCaseImpl
import com.project.data.interactors.GetRecipeByIdUseCaseImpl
import com.project.data.interactors.GetRecipesUseCaseImpl
import com.project.data.interactors.RecipesRepositoryImpl
import com.project.domain.recipeslist.RecipesRepository
import com.project.domain.recipeslist.usecase.EditRecipeUseCase
import com.project.domain.recipeslist.usecase.GetRecipeByIdUseCase
import com.project.domain.recipeslist.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RecipesModule {

    @Provides
    fun provideRecipesRepository(): RecipesRepository = RecipesRepositoryImpl()

    @Provides
    fun provideEditRecipeUseCase(repository: RecipesRepository): EditRecipeUseCase =
        EditRecipeUseCaseImpl(repository)

    @Provides
    fun provideGetRecipesUseCase(repository: RecipesRepository): GetRecipesUseCase =
        GetRecipesUseCaseImpl(repository)

    @Provides
    fun provideGetRecipeByIdUseCase(repository: RecipesRepository): GetRecipeByIdUseCase =
        GetRecipeByIdUseCaseImpl(repository)
}