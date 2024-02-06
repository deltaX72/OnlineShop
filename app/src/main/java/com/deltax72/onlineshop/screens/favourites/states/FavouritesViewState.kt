package com.deltax72.onlineshop.screens.favourites.states

sealed class FavouritesViewState {
    object Closed: FavouritesViewState()

    object Display: FavouritesViewState()
}
