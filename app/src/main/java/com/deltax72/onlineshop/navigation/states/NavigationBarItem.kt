package com.deltax72.onlineshop.navigation.states

import com.deltax72.onlineshop.R
import com.deltax72.onlineshop.screens.Screen

sealed class NavigationBarItem(
    open val title: String = "",
    open val icon: Int? = null,
    open val screen: Screen = Screen.None,
    open val onEvent: () -> Unit = {}
) {
    object None: NavigationBarItem()

    data class Home(
        override val title: String = "Главная",
        override val icon: Int? = R.drawable.icon_home,
        override val screen: Screen = Screen.Home,
        override val onEvent: () -> Unit = {}
    ): NavigationBarItem(
        title = title,
        icon = icon,
        screen = screen,
        onEvent = onEvent
    )

    data class Catalog(
        override val title: String = "Каталог",
        override val icon: Int? = R.drawable.icon_catalog,
        override val screen: Screen = Screen.Catalog,
        override val onEvent: () -> Unit = {}
    ): NavigationBarItem(
        title = title,
        icon = icon,
        screen = screen,
        onEvent = onEvent
    )

    data class Bag(
        override val title: String = "Корзина",
        override val icon: Int? = R.drawable.icon_bag,
        override val screen: Screen = Screen.Bag,
        override val onEvent: () -> Unit = {}
    ): NavigationBarItem(
        title = title,
        icon = icon,
        screen = screen,
        onEvent = onEvent
    )

    data class Discount(
        override val title: String = "Акции",
        override val icon: Int? = R.drawable.icon_discount,
        override val screen: Screen = Screen.Discount,
        override val onEvent: () -> Unit = {}
    ): NavigationBarItem(
        title = title,
        icon = icon,
        screen = screen,
        onEvent = onEvent
    )

    data class Account(
        override val title: String = "Профиль",
        override val icon: Int? = R.drawable.icon_account,
        override val screen: Screen = Screen.Account,
        override val onEvent: () -> Unit = {}
    ): NavigationBarItem(
        title = title,
        icon = icon,
        screen = screen,
        onEvent = onEvent
    )
}