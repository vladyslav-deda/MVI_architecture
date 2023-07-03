package com.project.presentation.recipeslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.domain.recipeslist.model.Recipe
import com.project.presentation.R

class RecipesAdapter(
    private val onItemClick: (recipe: Recipe) -> Unit
) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}