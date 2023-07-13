package com.project.presentation.recipedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.project.domain.recipeslist.model.Recipe
import com.project.presentation.databinding.FragmentRecipeDetailsBinding
import com.project.presentation.recipedetails.EditRecipeDialog.showEditRecipeDialog
import com.project.presentation.recipedetails.viewmodel.RecipeDetailsEvent
import com.project.presentation.recipedetails.viewmodel.RecipeDetailsViewModel
import com.project.presentation.recipeslist.RecipesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val viewModel by viewModels<RecipeDetailsViewModel>()

    private lateinit var binding: FragmentRecipeDetailsBinding

    private val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleEvent(RecipeDetailsEvent.LoadRecipe(args.itemId))
        observeState()
        initViews()
        RecipesListFragment.context = requireContext()
        Thread(RunnableForSecondTask()).also {
            it.start()
        }
    }

    private fun initViews() {
        binding.editChangeButton.setOnClickListener {
            viewModel.recipeState.value?.recipe?.let { recipe ->
                requireContext().showEditRecipeDialog(
                    recipeText = recipe.description,
                    onApplyClick = { description ->
                        viewModel.handleEvent(RecipeDetailsEvent.UpdateRecipeDescription(description))
                    }
                )
            }
        }
    }

    private fun observeState() {
        viewModel.recipeState.observe(viewLifecycleOwner) {
            binding.progressBar.root.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            it.recipe?.let { recipe ->
                binding.apply {
                    recipeTitle.text = recipe.title
                    recipeText.text = recipe.description
                }
            }
        }
    }

    inner class RunnableForSecondTask : Runnable {

        override fun run() {
            for (i in 1..999999) {
                val someObjectOfRecipe = Recipe(
                    id = i,
                    title = "new recipe",
                    description = "description for new recipe"
                )
            }
        }
    }
}