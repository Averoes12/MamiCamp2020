package com.raywenderlich.android.creatures.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.CreatureStore
import com.raywenderlich.android.creatures.ui.CreatureActivity
import kotlinx.android.synthetic.main.activity_creature.view.foodList
import kotlinx.android.synthetic.main.list_item_creature.view.creatureImage

class CreaturesWithFoodAdapter (private val creature:MutableList<Creature>) : RecyclerView.Adapter<CreaturesWithFoodAdapter.ViewHolder>() {


    val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent.inflate(R.layout.list_item_creature_with_food))
        holder.itemView.foodList.recycledViewPool = viewPool
        LinearSnapHelper().attachToRecyclerView(holder.itemView.foodList)
        return holder
    }

    override fun getItemCount(): Int = creature.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(creature = creature[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private lateinit var creature: Creature
        private val foodAdapter: FoodAdapter = FoodAdapter(mutableListOf())

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(creature: Creature){
            this.creature = creature
            val context = itemView.context
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))
            setUpFood()

        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }

        private fun setUpFood(){
            itemView.foodList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.foodList.adapter = foodAdapter

            val foods = CreatureStore.getCreatureFood(creature)
            foodAdapter.updateFood(foods)
        }
    }
}