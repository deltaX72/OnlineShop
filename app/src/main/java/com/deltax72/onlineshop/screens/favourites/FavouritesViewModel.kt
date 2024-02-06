package com.deltax72.onlineshop.screens.favourites

import androidx.lifecycle.viewModelScope
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.CatalogRepository
import com.deltax72.onlineshop.screens.favourites.states.FavouritesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val catalogRepository: CatalogRepository
): BaseViewModel<FavouritesEvent, FavouritesViewState>() {
    override val _state: MutableStateFlow<FavouritesViewState> =
        MutableStateFlow(FavouritesViewState.Closed)
    val state = _state.asStateFlow()

    private val _favourites: MutableStateFlow<List<Favourites>> =
        MutableStateFlow(emptyList())
    val favourites = _favourites.asStateFlow()

    override fun onEvent(event: FavouritesEvent) {
        when (val currentState = _state.value) {
            is FavouritesViewState.Closed -> reduce(event, currentState)
            is FavouritesViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: FavouritesEvent, currentState: FavouritesViewState.Closed) {
        when (event) {
            is FavouritesEvent.EnterScreen -> enterScreen()
            else -> {}
        }
    }

    private fun reduce(event: FavouritesEvent, currentState: FavouritesViewState.Display) {
        when (event) {
            is FavouritesEvent.ExitScreen -> exitScreen()
            is FavouritesEvent.OnAddToFavouritesClicked -> deleteFromFavourites(event.item)
            else -> {}
        }
    }

    private fun enterScreen() {
        viewModelScope.launch {
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
            _state.update {
                FavouritesViewState.Display
            }
        }
    }

    private fun exitScreen() {
        viewModelScope.launch {
            _state.update {
                FavouritesViewState.Closed
            }
        }
    }

    private fun deleteFromFavourites(item: ProductItem) {
        viewModelScope.launch {
            catalogRepository.deleteFromFavourites(item.id)
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
        }
    }
}