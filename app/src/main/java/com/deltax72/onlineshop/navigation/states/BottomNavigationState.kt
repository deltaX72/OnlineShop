package com.deltax72.onlineshop.navigation.states

data class BottomNavigationState(
    val activeState: NavigationBarActiveState = NavigationBarActiveState.Disabled,
    val items: NavigationBarItems = NavigationBarItems.None,
    val selectedItem: NavigationBarItem = NavigationBarItem.None
)