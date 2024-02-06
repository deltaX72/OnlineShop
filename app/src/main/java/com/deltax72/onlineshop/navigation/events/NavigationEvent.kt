package com.deltax72.onlineshop.navigation.events

import androidx.navigation.NavHostController
import com.deltax72.onlineshop.navigation.states.NavigationBarItem
import com.deltax72.onlineshop.navigation.states.NavigationBarItems
import com.deltax72.onlineshop.screens.Screen

sealed class NavigationEvent {
    data class EnterNavigation(
        val rootNavController: NavHostController
    ): NavigationEvent()

    object Exit: NavigationEvent()

    object EnableBottomNavigationBar: NavigationEvent()
    object DisableBottomNavigationBar: NavigationEvent()

    object EnableTopNavigationBar: NavigationEvent()
    object DisableTopNavigationBar: NavigationEvent()

    data class NavigateTo(
        val screen: Screen
    ): NavigationEvent()
    data class NavigateToWithPopUp(
        val navigateTo: Screen,
        val popUpTo: Screen
    ): NavigationEvent()

    data class NavigateToBottomNavigationItem(
        val navigateTo: Screen
    ): NavigationEvent()

    object PopBackStack: NavigationEvent()

    data class UpdateBottomNavigationBar(
        val selectedItem: NavigationBarItem,
        val items: NavigationBarItems
    ): NavigationEvent()

    object LogOut: NavigationEvent()
}
