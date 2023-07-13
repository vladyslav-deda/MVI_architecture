package com.project.presentation.recipedetails

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.project.presentation.R

object EditRecipeDialog {

    fun Context.showEditRecipeDialog(
        recipeText: String,
        onApplyClick: (newRecipeText: String) -> Unit
    ) = Dialog(this).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.edit_recipe_dialog_layout)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val recipeEditText = findViewById<TextInputEditText>(R.id.recipe_edittext)
        recipeEditText.setText(recipeText)
        findViewById<MaterialButton>(R.id.apply_change_button).setOnClickListener {
            if (!recipeEditText.text.toString().contentEquals(recipeText)) {
                onApplyClick(recipeEditText.text.toString())
            }
            dismiss()
        }
        findViewById<MaterialButton>(R.id.dismiss_button).setOnClickListener {
            dismiss()
        }

    }.show()
}