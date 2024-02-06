package com.deltax72.onlineshop.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.navigation.states.NavigationBarItem
import com.deltax72.onlineshop.navigation.states.NavigationBarItems
import com.deltax72.onlineshop.screens.Screen
import com.deltax72.onlineshop.screens.account.states.AccountViewState
import com.deltax72.onlineshop.screens.account.views.AccountViewDisplay
import com.deltax72.onlineshop.screens.catalog.CatalogEvent
import com.deltax72.onlineshop.screens.catalog.CatalogViewModel
import com.deltax72.onlineshop.screens.catalog.states.CatalogViewState
import com.deltax72.onlineshop.screens.details.DetailsViewModel
import com.deltax72.onlineshop.utils.rememberLifecycleEvent

@Composable
fun AccountScreen(
    navigationViewModel: NavigationViewModel,
    viewModel: AccountViewModel,
) {
    val state by viewModel.state.collectAsState()
    val favourites by viewModel.favourites.collectAsState()
    val authUserState by viewModel.authUserState.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()

    navigationViewModel.apply {
        onEvent(
            NavigationEvent.UpdateBottomNavigationBar(
                selectedItem = NavigationBarItem.Account(),
                items = NavigationBarItems.BottomMain(this)
            ),
            NavigationEvent.EnableBottomNavigationBar
        )
    }

    when (val currentState = state) {
        is AccountViewState.Display -> AccountViewDisplay(
            authUserState = authUserState,
            favourites = favourites,
            onFavouritesClicked = {
                navigationViewModel.onEvent(NavigationEvent.NavigateTo(Screen.Favourites))
            },
            onLogoutClicked = {
                navigationViewModel.onEvent(NavigationEvent.LogOut)
            }
        )
        else -> {}
    }

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            Lifecycle.Event.ON_START -> {
                viewModel.onEvent(AccountEvent.EnterScreen)
            }
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(AccountEvent.Display)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.onEvent(AccountEvent.ExitScreen)
            }
            else -> {}
        }
    }
}