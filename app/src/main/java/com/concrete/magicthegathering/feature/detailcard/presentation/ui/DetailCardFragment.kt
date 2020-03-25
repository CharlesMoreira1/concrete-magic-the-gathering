package com.concrete.magicthegathering.feature.detailcard.presentation.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel.DetailCardViewModel
import kotlinx.android.synthetic.main.fragment_detail_card.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCardFragment : Fragment(R.layout.fragment_detail_card), IDetailCardFragment {

    private val args: DetailCardFragmentArgs by navArgs()

    private val cardFavorite: CardFavorite by lazy {
        CardFavorite(imageCard = args.imageCard, idCard = args.idCard)
    }

    private val viewModel by viewModel<DetailCardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        navBackStack()
    }

    private fun loadData() {
        viewModel.findByCard(args.idCard)

        viewModel.getLiveDataCard.observe(viewLifecycleOwner, Observer {
            addCardToFavorite(it != null)
        })

        loadImageCard()
    }

    private fun loadImageCard(){
        text_name_detail_card.text = args.nameCard

        Glide.with(this)
            .load(args.imageCard)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    text_name_detail_card.visibility = View.VISIBLE
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    text_name_detail_card.visibility = View.GONE
                    return false
                }
            })
            .placeholder(R.drawable.card_empty)
            .into(image_detail_card)
    }

    override fun addCardToFavorite(insertEnabled: Boolean){
        button_favorite_card.setOnClickListener {
            viewModel.insertOrRemoveCard(insertEnabled, cardFavorite)
        }

        changeButtonFavorite(insertEnabled)
    }

    private fun changeButtonFavorite(insertEnabled: Boolean){
        button_favorite_card.text = getString(if (insertEnabled) R.string.name_button_favorite_remove
        else R.string.name_button_favorite_add)
    }

    private fun navBackStack(){
        image_button_closed.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
