package com.concrete.magicthegathering.feature.listset.helper

import com.concrete.magicthegathering.data.model.entity.cards.CardResponse
import retrofit2.Response
import kotlin.math.ceil

fun Response<CardResponse>.roundTotalPage(): Int {
    val valueDouble = (headers()["total-count"]!!.toInt() / headers()["page-size"]!!.toDouble())
    return ceil(valueDouble).toInt()
}