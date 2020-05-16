package com.raywenderlich.android.creatures.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.ui.CreatureActivity
import kotlinx.android.synthetic.main.list_item_creature.view.*

class CreaturesAdapter (private val creature:MutableList<Creature>) : RecyclerView.Adapter<CreaturesAdapter.ViewHolder>() {


    fun updateCreature(creature: List<Creature>){
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

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var creature: Creature

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(creature: Creature){
            this.creature = creature
            val context = itemView.context
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))
            itemView.fullName.text = creature.fullName
            itemView.fullName.text = creature.nickname

        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }
    }
}