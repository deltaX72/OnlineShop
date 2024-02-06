package com.deltax72.onlineshop.screens.account.states

import com.deltax72.onlineshop.screens.catalog.states.CatalogViewState

sealed class AccountViewState {
    object Loading: AccountViewState()

    object Display: AccountViewState()

    object Closed: AccountViewState()
}
