/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.creatures.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.adapter.FavoriteAdapter
import com.raywenderlich.android.creatures.model.CreatureStore
import com.raywenderlich.android.creatures.utils.DividingItemDecoration
import com.raywenderlich.android.creatures.utils.ItemDragListener
import com.raywenderlich.android.creatures.utils.ItemTouchHelperCallback
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment(), ItemDragListener {

  private var favoriteAdapter = FavoriteAdapter(mutableListOf(), this)
  private lateinit var itemTouchHelper: ItemTouchHelper

  companion object {
    fun newInstance(): FavoritesFragment {
      return FavoritesFragment()
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_favorites, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    favoriteList.layoutManager = LinearLayoutManager(activity)
    favoriteList.adapter = favoriteAdapter
    setUpItemTouchHelper()

    val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
    favoriteList.addItemDecoration(DividingItemDecoration(ContextCompat.getColor(context!!, R.color.colorDivider), heightInPixels))
  }

  override fun onResume() {
    super.onResume()
    val favorite = activity?.let { CreatureStore.getFavoriteCreature(it) }
    favorite?.let { favoriteAdapter.updateCreature(it) }
  }

  private fun setUpItemTouchHelper(){
    itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(favoriteAdapter))
    itemTouchHelper.attachToRecyclerView(favoriteList)
  }

  override fun onItemDrag(viewHolder: RecyclerView.ViewHolder) {
    itemTouchHelper.startDrag(viewHolder)
  }
}