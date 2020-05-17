package com.raywenderlich.android.creatures.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.CompositeItem
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.Favorites
import com.raywenderlich.android.creatures.ui.CreatureActivity
import com.raywenderlich.android.creatures.utils.ItemDragListener
import com.raywenderlich.android.creatures.utils.ItemSelectedListener
import com.raywenderlich.android.creatures.utils.ItemTouchHelperListener
import kotlinx.android.synthetic.main.list_item_creature.view.*
import kotlinx.android.synthetic.main.list_item_planet_header.view.*
import java.lang.IllegalArgumentException
import java.util.*

class FavoriteAdapter(private val creature: MutableList<Creature>, private val itemDrag: ItemDragListener) :
        RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(), ItemTouchHelperListener {


    fun updateCreature(creature: List<Creature>) {
        this.creature.clear()
        this.creature.addAll(creature)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature))

    }

    override fun getItemCount(): Int = creature.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(creature = creature[position])
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (creature[position].isHeader){
//            ViewType.HEADER.ordinal
//        }else{
//            ViewType.CREATURE.ordinal
//        }
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, ItemSelectedListener {

        private lateinit var creature: Creature

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(creature: Creature) {
//            if (creature.isHeader){
//                itemView.headerName.text = creature.header.name
//            }else{
            this.creature = creature
            val context = itemView.context
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(this.creature.uri, null, context.packageName))
            itemView.fullNames.text = this.creature.fullName
            itemView.nickname.text = this.creature.nickname
            animationView(itemView)
            itemView.handleDrag.setOnTouchListener{ _, event ->
                if (event.action == MotionEvent.ACTION_DOWN){
                    itemDrag.onItemDrag(this)
                }
                false
            }
//            }
        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }

        private fun animationView(viewToAnimation: View) {
            if (viewToAnimation.animation == null) {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_anim)
                viewToAnimation.animation = animation
            }
        }

        override fun onItemSelected() {
            itemView.listItemContainer.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.selectedItem))
        }

        override fun onItemCleared() {
            itemView.listItemContainer.setBackgroundColor(0)
        }
    }

    enum class ViewType {
        HEADER, CREATURE
    }

    override fun onItemMove(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(creature, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(creature, i, i - 1)
            }
        }
        Favorites.saveFavorites(creature.map { it.id }, recyclerView.context)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int) {
        Favorites.removeFavorite(creature[position], viewHolder.itemView.context)
        creature.removeAt(position)
        notifyItemRemoved(position)
    }
}