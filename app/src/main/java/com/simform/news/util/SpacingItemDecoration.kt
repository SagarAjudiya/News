package com.simform.news.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val verticalSpace: Int = 0,
    private val horizontalSpace: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            left = horizontalSpace
            right = horizontalSpace
            top = verticalSpace
            bottom = verticalSpace
        }
    }
}