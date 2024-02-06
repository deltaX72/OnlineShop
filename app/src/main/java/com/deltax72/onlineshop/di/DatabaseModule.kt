package com.deltax72.onlineshop.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.CatalogLocalDataSource

@Database(
    entities = [
        Favourites::class,
        ProductItem::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogLocalDataSource

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db"
                    )
                        .build()
                    instance = INSTANCE
                }
                return instance!!
            }
        }
    }
}