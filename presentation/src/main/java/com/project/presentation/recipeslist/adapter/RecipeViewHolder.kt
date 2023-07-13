package com.project.presentation.recipeslist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.domain.recipeslist.model.Recipe
import com.project.presentation.databinding.RecipeItemBinding

class RecipeViewHolder(
    itemView: View,
    private val onItemClick: (recipe: Recipe) -> Unit
) : ViewHolder(itemView) {

    private val binding = RecipeItemBinding.bind(itemView)

    fun bind(recipe: Recipe) {
        binding.apply {
            titleTextView.text = recipe.title
            descriptionTextView.text = recipe.description
        }
        itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }
}