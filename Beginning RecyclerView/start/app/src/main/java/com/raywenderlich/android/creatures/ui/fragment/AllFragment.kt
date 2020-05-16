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
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.adapter.CreaturesCardAdapter
import com.raywenderlich.android.creatures.model.CreatureStore
import com.raywenderlich.android.creatures.utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_all.*


class AllFragment : Fragment() {

    private val creaturesAdapter = CreaturesCardAdapter(CreatureStore.getCreatures().toMutableList())

    private lateinit var layoutManager: StaggeredGridLayoutManager
    private lateinit var listItemDecoration: RecyclerView.ItemDecoration
    private lateinit var gridItemDecoration: RecyclerView.ItemDecoration
    private lateinit var listMenuItem: MenuItem
    private lateinit var gridMenuItem: MenuItem
    private var gridState = GridState.GRID

    companion object {
        fun newInstance(): AllFragment {
            return AllFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

        creatureList.layoutManager = layoutManager
        creatureList.adapter = creaturesAdapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.creature_card_grid_layout_margin)

        listItemDecoration = SpacingItemDecoration(1, spacingInPixels)
        gridItemDecoration = SpacingItemDecoration(2, spacingInPixels)

        creatureList.addItemDecoration(gridItemDecoration)
    }

    private fun updateRecyclerView(spanCount: Int, addItemDecoration: RecyclerView.ItemDecoration,
                                   removingItemDecoration: RecyclerView.ItemDecoration) {
        layoutManager.spanCount = spanCount
        creatureList.removeItemDecoration(removingItemDecoration)
        creatureList.addItemDecoration(addItemDecoration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        listMenuItem = menu.findItem(R.id.action_span_1)
        gridMenuItem = menu.findItem(R.id.action_span_2)

        when (gridState) {
            GridState.GRID -> {
                gridMenuItem.isEnabled = false
                listMenuItem.isEnabled = true
            }

          GridState.LIST -> {
            listMenuItem.isEnabled = false
            gridMenuItem.isEnabled = true
          }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_all, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_span_1 -> {
              gridState = GridState.LIST
              updateRecyclerView(1, listItemDecoration, gridItemDecoration)
            }
            R.id.action_span_2 -> {
              gridState = GridState.GRID
              updateRecyclerView(2, gridItemDecoration, listItemDecoration)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private enum class GridState {
        LIST, GRID
    }
}