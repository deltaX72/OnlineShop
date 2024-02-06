package com.deltax72.onlineshop.screens.account

import com.deltax72.onlineshop.screens.catalog.CatalogEvent

sealed class AccountEvent {
    object EnterScreen: AccountEvent()

    object Display: AccountEvent()

    object ExitScreen: AccountEvent()
}