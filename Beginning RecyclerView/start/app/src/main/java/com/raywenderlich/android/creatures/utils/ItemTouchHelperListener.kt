package com.raywenderlich.android.creatures.utils

import android.support.v7.widget.RecyclerView
import java.text.FieldPosition

interface ItemTouchHelperListener {

    fun onItemMove(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int) : Boolean
}