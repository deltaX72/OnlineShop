package com.deltax72.onlineshop.utils

import androidx.navigation.NavHostController

//fun NavHostController.navigateAndReplaceStartRoute(newScreen: Screen) {
//    popBackStack(graph.startDestinationId, true)
//    graph.setStartDestination(newScreen.route)
//    navigate(newScreen.route)
//}
//
//fun NavHostController.navigateAndReplaceStartRoute(newScreen: NavGraphRoute) {
//    popBackStack(graph.startDestinationId, true)
//    graph.setStartDestination(newScreen.route)
//    navigate(newScreen.route)
//}

fun NavHostController.navigateWithPopUp(
    popUpTo: String,
    navigateTo: String,
    inclusive: Boolean = false,
    saveState: Boolean = false
) {
    navigate(navigateTo) {
        this.popUpTo(popUpTo) {
            this.inclusive = inclusive
            this.saveState = saveState
        }
    }
}