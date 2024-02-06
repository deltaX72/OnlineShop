package com.deltax72.onlineshop.screens.catalog

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
import com.deltax72.onlineshop.screens.catalog.states.CatalogViewState
import com.deltax72.onlineshop.screens.catalog.views.CatalogViewDisplay
import com.deltax72.onlineshop.screens.details.DetailsViewModel
import com.deltax72.onlineshop.screens.registration.RegistrationEvent
import com.deltax72.onlineshop.utils.rememberLifecycleEvent

@Composable
fun CatalogScreen(
    navigationViewModel: NavigationViewModel,
    viewModel: CatalogViewModel,
    sharedViewModel: DetailsViewModel
) {
    val state by viewModel.state.collectAsState()

    val items by viewModel.sortedItems.collectAsState()
    val favourites by viewModel.favourites.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()

    navigationViewModel.apply {
        onEvent(
            NavigationEvent.UpdateBottomNavigationBar(
                selectedItem = NavigationBarItem.Catalog(),
                items = NavigationBarItems.BottomMain(this)
            ),
            NavigationEvent.EnableBottomNavigationBar
        )
    }

    when (val currentState = state) {
        is CatalogViewState.Display -> CatalogViewDisplay(
            items = items,
            favourites = favourites,
            onByPopularitySelected = {
                viewModel.onEvent(CatalogEvent.SortByPopularity)
            },
            onByPriceReductionSelected = {
                viewModel.onEvent(CatalogEvent.SortByPriceReduction)
            },
            onByPriceIncreaseSelected = {
                viewModel.onEvent(CatalogEvent.SortByPriceIncrease)
            },
            onCarouselItemSelected = { tag ->
                viewModel.onEvent(CatalogEvent.OnTagSelected(tag))
            },
            onItemClicked = { item, images ->
                sharedViewModel.setItem(item)
                sharedViewModel.setImages(images)
                navigationViewModel.onEvent(NavigationEvent.NavigateTo(Screen.Details))
            },
            onAddToFavouritesClicked = { item ->
                viewModel.onEvent(CatalogEvent.OnAddToFavouritesClicked(item))
            }
        )
        else -> {}
    }

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            Lifecycle.Event.ON_START -> {
                viewModel.onEvent(CatalogEvent.EnterScreen)
            }
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(CatalogEvent.Display)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.onEvent(CatalogEvent.ExitScreen)
            }
            else -> {}
        }
    }
}