package com.deltax72.onlineshop.screens.catalog

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.ProductItem

@Dao
interface CatalogLocalDataSource {

    @Insert
    suspend fun addToFavourites(item: Favourites)

    @Query("delete from favourites where `id` = :id")
    suspend fun deleteFromFavourites(id: String)

    @Query("select * from favourites")
    suspend fun selectAll(): List<Favourites>
}