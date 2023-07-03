package com.project.presentation.recipeslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.presentation.R
import com.project.presentation.databinding.FragmentRecipesListBinding
import com.project.presentation.recipeslist.adapter.RecipesAdapter
import com.project.presentation.recipeslist.adapter.RecyclerViewItemDecoration
import com.project.presentation.recipeslist.viewmodel.RecipesListEvent
import com.project.presentation.recipeslist.viewmodel.RecipesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListFragment : Fragment() {

    private val viewModel by viewModels<RecipesListViewModel>()

    private lateinit var binding: FragmentRecipesListBinding

    private var recipesAdapter: RecipesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
        initRecyclerView()
        viewModel.handleEvent(RecipesListEvent.LoadRecipes)
    }

    private fun observeStates() {
        viewModel.recipesState.observe(viewLifecycleOwner) {
            binding.progressBar.root.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            it.recipesList?.let { recipesList ->
                recipesAdapter?.submitList(recipesList)
            }
        }
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesAdapter { item ->
            findNavController().navigate(
                RecipesListFragmentDirections.actionRecipesListFragmentToRecipeDetailsFragment(item.id)
            )
        }
        binding.recipesItems.apply {
            adapter = recipesAdapter
            addItemDecoration(
                RecyclerViewItemDecoration(
                    requireContext().getColor(
                        R.color.red
                    )
                )
            )
        }
    }
}