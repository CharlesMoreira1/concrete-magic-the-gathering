package com.concrete.magicthegathering.feature.detailcard.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.concrete.magicthegathering.R
import kotlinx.android.synthetic.main.fragment_detail_card.*

class DetailCardFragment : Fragment(R.layout.fragment_detail_card) {

    private val args: DetailCardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        navBackStack()
    }

    private fun loadData(){
        Glide.with(this)
            .load(args.imageCard)
            .placeholder(R.drawable.image_not_found)
            .error(R.drawable.image_not_found)
            .into(image_detail_card)
    }

    private fun navBackStack(){
        image_button_closed.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
