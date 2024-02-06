package com.deltax72.onlineshop.screens.catalog.states

sealed class CatalogViewState {
    object Loading: CatalogViewState()

    object Display: CatalogViewState()

    object Closed: CatalogViewState()
}
