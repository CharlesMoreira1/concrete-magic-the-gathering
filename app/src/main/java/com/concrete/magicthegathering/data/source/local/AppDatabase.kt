package com.concrete.magicthegathering.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.data.source.local.dao.FavoriteDao

@Database(entities = [CardFavorite::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_database")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}