package com.concrete.magicthegathering.feature.listset.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.data.model.entity.cards.Card
import kotlinx.android.synthetic.main.item_card.view.*

class SetAdapter : RecyclerView.Adapter<SetAdapter.ItemViewHolder>() {

    private var listCards = ArrayList<Card>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = listCards.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {
        val dataItem = listCards[p1]
        holder.bindView(dataItem)
    }

    fun addList(listCards: List<Card>) {
        this.listCards.addAll(listCards)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.listCards.clear()
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(card: Card) = with(view){
            Glide.with(context)
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_image_24dp)
                .error(R.drawable.ic_image_24dp)
                .into(view.image_cover)
        }
    }
}