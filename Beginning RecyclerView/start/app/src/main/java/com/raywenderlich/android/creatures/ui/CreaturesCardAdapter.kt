package com.raywenderlich.android.creatures.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import kotlinx.android.synthetic.main.list_item_creature.view.*
import kotlinx.android.synthetic.main.list_item_creature.view.nickname
import kotlinx.android.synthetic.main.list_item_creature_card.view.*

class CreaturesCardAdapter (private val creature:MutableList<Creature>) : RecyclerView.Adapter<CreaturesCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature_card))
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
            val imageResources = context.resources.getIdentifier(creature.uri, null, context.packageName)
            itemView.creatureImageCard.setImageResource(imageResources)
            itemView.nickname.text = creature.nickname
            setBackgroundColors(context, imageResources)
        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }

        private  fun setBackgroundColors(context: Context, imageResources: Int ){
            val image = BitmapFactory.decodeResource(context.resources, imageResources)
            Palette.from(image).generate{
                palette ->
                val background = palette.getDominantColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                itemView.creatureCard.setBackgroundColor(background)
                itemView.nicknameHolder.setBackgroundColor(background)
                val textColor = if (isColorDark(background)) Color.WHITE else Color.BLACK
                itemView.nickname.setTextColor(textColor)
            }
        }

        private fun isColorDark(color: Int): Boolean {
            val darkness  = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
            return darkness >= 0.5
        }
    }
}