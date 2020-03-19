package com.concrete.magicthegathering.data.model.entity.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class CardFavorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imageCard: String?,
    val multiverseid: Long
)