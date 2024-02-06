package com.deltax72.onlineshop.screens.favourites

import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.CatalogEvent

sealed class FavouritesEvent {
    object EnterScreen: FavouritesEvent()

    object Display: FavouritesEvent()

    object ExitScreen: FavouritesEvent()

    data class OnAddToFavouritesClicked(val item: ProductItem): FavouritesEvent()
}
