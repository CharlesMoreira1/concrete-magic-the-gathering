package com.concrete.magicthegathering.data.model.entity.sets

import com.google.gson.annotations.SerializedName

data class SetReponse(
    @SerializedName("sets")
    val sets: List<Set>
)