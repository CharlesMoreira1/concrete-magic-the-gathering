package com.concrete.magicthegathering.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite WHERE multiverseid = :multiverseid")
    fun findByCard(multiverseid: Long): LiveData<CardFavorite?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cardFavorite: CardFavorite)

    @Query("DELETE FROM favorite WHERE multiverseid = :multiverseid")
    suspend fun removeCard(multiverseid: Long)
}