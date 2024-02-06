package com.deltax72.onlineshop.navigation.states

import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.screens.Screen

sealed class NavigationBarItems(
    open val navigationViewModel: NavigationViewModel?,
    val items: List<NavigationBarItem>
) {
    object None : NavigationBarItems(null, emptyList())

    data class BottomMain(
        override val navigationViewModel: NavigationViewModel?,
    ): NavigationBarItems(
        navigationViewModel,
        items = listOf(
            NavigationBarItem.Home {
                navigationViewModel?.onEvent(NavigationEvent.NavigateToBottomNavigationItem(Screen.Home))
            },
            NavigationBarItem.Catalog {
                navigationViewModel?.onEvent(NavigationEvent.NavigateToBottomNavigationItem(Screen.Catalog))
            },
            NavigationBarItem.Bag {
                navigationViewModel?.onEvent(NavigationEvent.NavigateToBottomNavigationItem(Screen.Bag))
            },
            NavigationBarItem.Discount {
                navigationViewModel?.onEvent(NavigationEvent.NavigateToBottomNavigationItem(Screen.Discount))
            },
            NavigationBarItem.Account {
                navigationViewModel?.onEvent(NavigationEvent.NavigateToBottomNavigationItem(Screen.Account))
            }
        )
    )
}