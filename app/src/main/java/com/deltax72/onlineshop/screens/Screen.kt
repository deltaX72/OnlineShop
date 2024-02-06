package com.deltax72.onlineshop.screens

sealed class Screen(val route: String) {
    object None: Screen("none_screen")

    object Login: Screen("login_screen")
    object Registration: Screen("registration_screen")

    object Home: Screen("home_screen")
    object Catalog: Screen("catalog_screen")
    object Bag: Screen("bag_screen")
    object Discount: Screen("discount_screen")
    object Account: Screen("account_screen")

    object Details: Screen("details_screen")
    object Favourites: Screen("favourites_screen")
}
