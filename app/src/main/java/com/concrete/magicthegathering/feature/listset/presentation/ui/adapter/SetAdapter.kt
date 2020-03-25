package com.concrete.magicthegathering.feature.listset.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.SetDomain
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.item_header_set.view.*
import com.bumptech.glide.request.target.Target
import com.concrete.magicthegathering.data.model.ItemType
import com.concrete.magicthegathering.data.model.domain.TypeSetDomain

class SetAdapter(private val onItemClickListener: ((CardDomain) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItemType = ArrayList<ItemType>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when (p1) {
            ItemType.ITEM_HEADER_SET -> {
                HeaderSetViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_header_set, p0, false))
            }
            ItemType.ITEM_HEADER_TYPE_CARD -> {
                HeaderTypeCardViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_header_card, p0, false))
            }
            else -> {
                CardsViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false), onItemClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listItemType[position].type
    }

    override fun getItemCount(): Int = listItemType.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        when (holder) {
            is HeaderSetViewHolder -> {
                val dataItem = listItemType[p1] as SetDomain
                holder.bindView(dataItem)
            }

            is HeaderTypeCardViewHolder -> {
                val dataItem = listItemType[p1] as TypeSetDomain
                holder.bindView(dataItem)
            }
            is CardsViewHolder -> {
                val dataItem = listItemType[p1] as CardDomain
                holder.bindView(dataItem)
            }
        }
    }

    fun addList(listItemType: List<ItemType>) {
        this.listItemType.addAll(listItemType)
        notifyItemRangeInserted(this.listItemType.size - listItemType.size, this.listItemType.size)
    }

    fun clearList(){
        this.listItemType.clear()
        notifyDataSetChanged()
    }

    class HeaderSetViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(setDomain: SetDomain) = with(view){
            this.text_name_set.text = setDomain.nameSet
        }
    }

    class HeaderTypeCardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(typeCardDomain: TypeSetDomain) = with(view){
            this.text_name_card.text = typeCardDomain.nameType
        }
    }

    class CardsViewHolder(private val view: View, private val onItemClickListener: ((CardDomain) -> Unit)) : RecyclerView.ViewHolder(view) {

        fun bindView(cardDomain: CardDomain) = with(view){

            this.text_name_card.text = cardDomain.name

            Glide.with(context)
                .load(cardDomain.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        text_name_card.visibility = View.VISIBLE
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        text_name_card.visibility = View.GONE
                        return false
                    }
                })
                .placeholder(R.drawable.card_empty)
                .into(this.image_cover)

            this.setOnClickListener {
                onItemClickListener.invoke(cardDomain)
            }
        }
    }
}