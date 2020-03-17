package com.concrete.magicthegathering.feature.listset.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.core.util.fadeAnimation
import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.SetDomain
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.item_header_set.view.*

class SetAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData = ArrayList<Any>()

    companion object {
        const val ITEM_HEADER = 0
        const val ITEM_LIST_CARDS = 1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return if (p1 == ITEM_HEADER) {
            HeaderViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_header_set, p0, false))
        } else {
            CardsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listData[position]) {
            is SetDomain -> ITEM_HEADER
            is CardDomain -> ITEM_LIST_CARDS
            else -> position
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val dataItem = listData[p1] as SetDomain
                holder.bindView(dataItem)
            }
            is CardsViewHolder -> {
                val dataItem = listData[p1] as CardDomain
                holder.bindView(dataItem)
            }
        }
    }

    fun addList(setDomain: SetDomain) {
        this.listData.add(setDomain)
        this.listData.addAll(setDomain.listCardDomain)
        notifyItemChanged(this.listData.size - setDomain.listCardDomain.size, this.listData.size)
    }

    fun clearList(){
        this.listData.clear()
        notifyDataSetChanged()
    }

    class HeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(setDomain: SetDomain) = with(view){
            this.text_name_set.text = setDomain.nameSet
        }
    }

    class CardsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(cardDomain: CardDomain) = with(view){

            this.image_cover.fadeAnimation()

            Glide.with(context)
                .load(cardDomain.image)
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(this.image_cover)
        }
    }

}