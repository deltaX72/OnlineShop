package com.deltax72.onlineshop.screens.favourites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.screens.Screen
import com.deltax72.onlineshop.screens.details.DetailsEvent
import com.deltax72.onlineshop.screens.details.DetailsViewModel
import com.deltax72.onlineshop.screens.details.states.DetailsViewState
import com.deltax72.onlineshop.screens.details.views.DetailsViewDisplay
import com.deltax72.onlineshop.screens.favourites.states.FavouritesViewState
import com.deltax72.onlineshop.screens.favourites.views.FavouritesViewDisplay
import com.deltax72.onlineshop.utils.rememberLifecycleEvent

@Composable
fun FavouritesScreen(
    navigationViewModel: NavigationViewModel,
    viewModel: FavouritesViewModel,
    sharedViewModel: DetailsViewModel
) {
    val state by viewModel.state.collectAsState()
    val favourites by viewModel.favourites.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()

    navigationViewModel.apply {
        onEvent(
            NavigationEvent.DisableBottomNavigationBar
        )
    }

    when (val currentState = state) {
        is FavouritesViewState.Display -> FavouritesViewDisplay(
            favourites = favourites,
            onAddToFavouritesClicked = { item ->
                viewModel.onEvent(FavouritesEvent.OnAddToFavouritesClicked(item))
            },
            onBackClicked = {
                navigationViewModel.onEvent(NavigationEvent.PopBackStack)
            },
            onItemClicked = { item, images ->
                sharedViewModel.setItem(item)
                sharedViewModel.setImages(images)
                navigationViewModel.onEvent(NavigationEvent.NavigateTo(Screen.Details))
            }
        )
        else -> {}
    }

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            Lifecycle.Event.ON_START -> {
                viewModel.onEvent(FavouritesEvent.EnterScreen)
            }
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(FavouritesEvent.Display)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.onEvent(FavouritesEvent.ExitScreen)
            }
            else -> {}
        }
    }
}