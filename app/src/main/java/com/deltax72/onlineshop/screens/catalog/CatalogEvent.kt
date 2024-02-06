package com.deltax72.onlineshop.screens.catalog

import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.states.Tag


sealed class CatalogEvent {
    object EnterScreen: CatalogEvent()

    object Display: CatalogEvent()

    object ExitScreen: CatalogEvent()

    object SortByPopularity: CatalogEvent()

    object SortByPriceIncrease: CatalogEvent()

    object SortByPriceReduction: CatalogEvent()

    data class OnTagSelected(val tag: Tag): CatalogEvent()

    data class OnAddToFavouritesClicked(val item: ProductItem): CatalogEvent()
}
