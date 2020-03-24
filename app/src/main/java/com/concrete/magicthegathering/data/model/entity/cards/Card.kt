package com.concrete.magicthegathering.data.model.entity.cards

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("artist")
    val artist: String = "",
    @SerializedName("cmc")
    val cmc: Double = 0.0,
    @SerializedName("colorIdentity")
    val colorIdentity: List<String> = listOf(),
    @SerializedName("colors")
    val colors: List<String> = listOf(),
    @SerializedName("flavor")
    val flavor: String = "",
    @SerializedName("foreignNames")
    val foreignNames: List<Any> = listOf(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("imageUrl")
    val imageUrl: String = "",
    @SerializedName("layout")
    val layout: String = "",
    @SerializedName("legalities")
    val legalities: List<Legality> = listOf(),
    @SerializedName("manaCost")
    val manaCost: String = "",
    @SerializedName("multiverseid")
    val multiverseid: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("number")
    val number: String = "",
    @SerializedName("originalText")
    val originalText: String = "",
    @SerializedName("originalType")
    val originalType: String = "",
    @SerializedName("power")
    val power: String = "",
    @SerializedName("printings")
    val printings: List<String> = listOf(),
    @SerializedName("rarity")
    val rarity: String = "",
    @SerializedName("rulings")
    val rulings: List<Any> = listOf(),
    @SerializedName("set")
    val setCode: String = "",
    @SerializedName("setName")
    val setName: String = "",
    @SerializedName("subtypes")
    val subtypes: List<Any> = listOf(),
    @SerializedName("supertypes")
    val supertypes: List<Any> = listOf(),
    @SerializedName("text")
    val text: String = "",
    @SerializedName("toughness")
    val toughness: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("types")
    val types: List<String> = listOf(),
    @SerializedName("variations")
    val variations: List<String> = listOf()
)