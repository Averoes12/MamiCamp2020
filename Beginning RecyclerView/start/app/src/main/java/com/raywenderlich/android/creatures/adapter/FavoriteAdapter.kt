package com.raywenderlich.android.creatures.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.CompositeItem
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.ui.CreatureActivity
import kotlinx.android.synthetic.main.list_item_creature.view.*
import kotlinx.android.synthetic.main.list_item_planet_header.view.*
import java.lang.IllegalArgumentException

class FavoriteAdapter (private val compositesItem:MutableList<CompositeItem>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    fun updateCreature(creature: List<CompositeItem>){
        this.compositesItem.clear()
        this.compositesItem.addAll(creature)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            ViewType.HEADER.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_planet_header))
            ViewType.CREATURE.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_creature))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = compositesItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(compositesItem = compositesItem[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (compositesItem[position].isHeader){
            ViewType.HEADER.ordinal
        }else{
            ViewType.CREATURE.ordinal
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var creature: Creature

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(compositesItem: CompositeItem){
            if (compositesItem.isHeader){
                itemView.headerName.text = compositesItem.header.name
            }else{
                this.creature = compositesItem.creature
                val context = itemView.context
                itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))
                itemView.fullNames.text = creature.fullName
                itemView.nickname.text = creature.nickname
                animationView(itemView)
            }
        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }

        private fun animationView(viewToAnimation:View){
            if (viewToAnimation.animation == null){
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_anim)
                viewToAnimation.animation = animation
            }
        }
    }
    enum class ViewType{
        HEADER, CREATURE
    }
}