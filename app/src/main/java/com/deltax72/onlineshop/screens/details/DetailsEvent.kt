package com.deltax72.onlineshop.screens.details

import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.registration.RegistrationEvent

sealed class DetailsEvent {
    object EnterScreen: DetailsEvent()

    object Display: DetailsEvent()

    object ExitScreen: DetailsEvent()

    data class OnAddToFavouritesClicked(val item: ProductItem): DetailsEvent()
}