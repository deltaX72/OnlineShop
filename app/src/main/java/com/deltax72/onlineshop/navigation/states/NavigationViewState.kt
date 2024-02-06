package com.deltax72.onlineshop.navigation.states

sealed class NavigationViewState {
    object Display: NavigationViewState()

    object Closed: NavigationViewState()

    object Exit: NavigationViewState()
}