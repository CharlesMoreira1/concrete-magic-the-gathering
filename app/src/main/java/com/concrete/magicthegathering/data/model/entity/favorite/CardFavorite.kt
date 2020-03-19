package com.concrete.magicthegathering.data.model.entity.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class CardFavorite(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var imageCard: String?,
    var multiverseid: Long
)