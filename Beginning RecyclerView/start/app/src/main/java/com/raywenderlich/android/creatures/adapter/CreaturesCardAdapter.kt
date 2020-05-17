package com.raywenderlich.android.creatures.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.Constant
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.ui.CreatureActivity
import kotlinx.android.synthetic.main.list_item_creature_card.view.*
import kotlinx.android.synthetic.main.list_item_creature_card.view.creatureCardContainer
import kotlinx.android.synthetic.main.list_item_creature_card.view.creatureImageCard
import kotlinx.android.synthetic.main.list_item_creature_card.view.fullName
import kotlinx.android.synthetic.main.list_item_creature_card.view.nameHolder
import kotlinx.android.synthetic.main.list_item_creature_card_jupiter.view.*
import java.lang.IllegalArgumentException

class CreaturesCardAdapter(private val creature: MutableList<Creature>) : RecyclerView.Adapter<CreaturesCardAdapter.ViewHolder>() {

    var scrollDirection = ScrollDirection.DOWN
    var jupiterSpanSize = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            ViewType.JUPITER.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_creature_card_jupiter))
            ViewType.OTHER.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_creature_card))
            ViewType.MARS.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_creature_card_mars))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = creature.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(creature = creature[position])
    }

    override fun getItemViewType(position: Int): Int {
        val creature = creature[position]
        return if (creature.planet == Constant.JUPITER){
            ViewType.JUPITER.ordinal
        }else if (creature.planet == Constant.MARS){
            ViewType.MARS.ordinal
        } else {
            ViewType.OTHER.ordinal
        }
    }

    fun spanSizePosition(position: Int): Int {
        return if (creature[position].planet == Constant.JUPITER){
            jupiterSpanSize
        }else{
            1
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var creature: Creature

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(creature: Creature) {
            this.creature = creature
            val context = itemView.context
            val imageResources = context.resources.getIdentifier(creature.uri, null, context.packageName)
            itemView.creatureImageCard.setImageResource(imageResources)
            itemView.fullName.text = creature.fullName
            setBackgroundColors(context, imageResources)
            animationView(itemView)
        }

        override fun onClick(view: View) {

            val context = view.context
            val detailScreen = CreatureActivity.newIntent(context, creatureId = creature.id)
            context.startActivity(detailScreen)
        }

        private fun setBackgroundColors(context: Context, imageResources: Int) {
            val image = BitmapFactory.decodeResource(context.resources, imageResources)
            Palette.from(image).generate { palette ->
                val background = palette.getDominantColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                itemView.creatureCardContainer.setBackgroundColor(background)
                itemView.nameHolder.setBackgroundColor(background)
                val textColor = if (isColorDark(background)) Color.WHITE else Color.BLACK
                itemView.fullName.setTextColor(textColor)
                if (itemView.slogan != null){
                    itemView.slogan.setTextColor(textColor)
                }
            }
        }

        private fun isColorDark(color: Int): Boolean {
            val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
            return darkness >= 0.5
        }

        private fun animationView(viewToAnimation: View) {
            if (viewToAnimation.animation == null) {
//                val animId = if (scrollDirection == ScrollDirection.DOWN) R.anim.slide_from_bottom else R.anim.slide_from_top
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_anim)
                viewToAnimation.animation = animation
            }
        }
    }

    public enum class ScrollDirection {
        UP, DOWN
    }

    enum class ViewType{
        JUPITER,MARS ,OTHER
    }
}