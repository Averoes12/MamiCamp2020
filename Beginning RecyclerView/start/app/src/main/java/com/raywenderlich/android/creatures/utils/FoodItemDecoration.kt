package com.raywenderlich.android.creatures.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

class FoodItemDecoration(color:Int, private val dividerWithInPixels:Int): RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount

        for (i in 0 until childCount){
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            //Top divider
            var left = parent.paddingLeft
            var right = parent.width - parent.paddingRight
            var top = child.top + params.topMargin
            var bottom = top + dividerWithInPixels
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)

            //Right divider
            left = child.right - params.rightMargin
            right = left + dividerWithInPixels
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)

        }
    }

}