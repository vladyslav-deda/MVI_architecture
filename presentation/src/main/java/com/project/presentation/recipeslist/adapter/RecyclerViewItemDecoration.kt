package com.project.presentation.recipeslist.adapter


import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(color: Int) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION && position != 0 && position != state.itemCount - 1) {
            outRect.top = SPACE_HEIGHT
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 1 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.top - params.topMargin - SPACE_HEIGHT
            val bottom = top + SPACE_HEIGHT

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    companion object {
        private const val SPACE_HEIGHT = 2
    }
}