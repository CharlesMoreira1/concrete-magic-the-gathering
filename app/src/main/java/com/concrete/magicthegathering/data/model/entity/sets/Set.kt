package com.concrete.magicthegathering.data.model.entity.sets

import com.google.gson.annotations.SerializedName

data class Set(
    @SerializedName("block")
    val block: String = "",
    @SerializedName("booster")
    val booster: List<Any> = listOf(),
    @SerializedName("code")
    val code: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("onlineOnly")
    val onlineOnly: Boolean = false,
    @SerializedName("releaseDate")
    val releaseDate: String = "",
    @SerializedName("type")
    val type: String = ""
)