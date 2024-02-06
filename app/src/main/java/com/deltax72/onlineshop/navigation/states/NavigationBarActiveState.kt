package com.deltax72.onlineshop.navigation.states

sealed class NavigationBarActiveState {
    object Enabled: NavigationBarActiveState()

    object Disabled: NavigationBarActiveState()
}