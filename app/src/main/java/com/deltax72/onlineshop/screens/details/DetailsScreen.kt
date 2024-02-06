package com.deltax72.onlineshop.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.screens.details.states.DetailsViewState
import com.deltax72.onlineshop.screens.details.views.DetailsViewDisplay
import com.deltax72.onlineshop.screens.registration.RegistrationEvent
import com.deltax72.onlineshop.screens.registration.RegistrationViewModel
import com.deltax72.onlineshop.utils.rememberLifecycleEvent

@Composable
fun DetailsScreen(
    navigationViewModel: NavigationViewModel,
    viewModel: DetailsViewModel
) {
    val state by viewModel.state.collectAsState()
    val item by viewModel.item.collectAsState()
    val images by viewModel.images.collectAsState()
    val favourites by viewModel.favourites.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()

    navigationViewModel.apply {
        onEvent(
            NavigationEvent.DisableBottomNavigationBar
        )
    }

    when (val currentState = state) {
        is DetailsViewState.Display -> DetailsViewDisplay(
            bitmapImages = images,
            item = item,
            favourites = favourites,
            onAddToFavouritesClicked = { item ->
                viewModel.onEvent(DetailsEvent.OnAddToFavouritesClicked(item))
            },
            onBackClicked = {
                navigationViewModel.onEvent(NavigationEvent.PopBackStack)
            }
        )
        else -> {}
    }

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            Lifecycle.Event.ON_START -> {
                viewModel.onEvent(DetailsEvent.EnterScreen)
            }
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(DetailsEvent.Display)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.onEvent(DetailsEvent.ExitScreen)
            }
            else -> {}
        }
    }
}