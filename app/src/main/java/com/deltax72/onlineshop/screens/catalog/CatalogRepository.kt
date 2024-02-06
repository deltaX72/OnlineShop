package com.deltax72.onlineshop.screens.catalog

import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.Items
import com.deltax72.onlineshop.data.entities.ProductItem

class CatalogRepository(
    private val localDataSource: CatalogLocalDataSource,
    private val remoteDataSource: CatalogRemoteDataSource
) {

    suspend fun getItems(): Items {
        return remoteDataSource.getItems()
    }

    suspend fun addToFavourites(item: Favourites) {
        localDataSource.addToFavourites(item)
    }

    suspend fun deleteFromFavourites(id: String) {
        localDataSource.deleteFromFavourites(id)
    }

    suspend fun selectAllFavourites(): List<Favourites> {
        return localDataSource.selectAll()
    }
}