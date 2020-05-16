package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Food
import kotlinx.android.synthetic.main.list_item_food.view.*

class FoodAdapter(private  val foods: MutableList<Food>): RecyclerView.Adapter<FoodAdapter.Holder>() {


    fun updateFood(food: List<Food>){
        this.foods.clear()
        this.foods.addAll(food)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(parent.inflate(R.layout.list_item_food))
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(food = foods[position])
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private lateinit var food: Food

        fun bind(food: Food){
            this.food = food
            val context = itemView.context
            itemView.foodImage.setImageResource(context.resources.getIdentifier(food.thumbnails, null, context.packageName))
        }

    }
}