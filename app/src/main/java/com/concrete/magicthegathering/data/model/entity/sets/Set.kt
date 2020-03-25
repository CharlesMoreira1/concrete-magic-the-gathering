package com.concrete.magicthegathering.data.model.entity.sets

import com.google.gson.annotations.SerializedName

data class Set(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("releaseDate")
    val releaseDate: String
)