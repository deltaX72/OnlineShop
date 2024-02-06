package com.deltax72.onlineshop.screens.details

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.CatalogEvent
import com.deltax72.onlineshop.screens.catalog.CatalogRepository
import com.deltax72.onlineshop.screens.catalog.states.CatalogViewState
import com.deltax72.onlineshop.screens.details.states.DetailsViewState
import com.deltax72.onlineshop.screens.registration.RegistrationEvent
import com.deltax72.onlineshop.screens.registration.states.RegistrationViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val catalogRepository: CatalogRepository
): BaseViewModel<DetailsEvent, DetailsViewState>() {
    override val _state: MutableStateFlow<DetailsViewState> =
        MutableStateFlow(DetailsViewState.Closed)
    val state = _state.asStateFlow()

    private val _item: MutableStateFlow<ProductItem> =
        MutableStateFlow(ProductItem())
    val item = _item.asStateFlow()

    private val _images: MutableStateFlow<List<ImageBitmap>> =
        MutableStateFlow(emptyList())
    val images = _images.asStateFlow()

    private val _favourites: MutableStateFlow<List<Favourites>> =
        MutableStateFlow(emptyList())
    val favourites = _favourites.asStateFlow()

    override fun onEvent(event: DetailsEvent) {
        when (val currentState = _state.value) {
            is DetailsViewState.Closed -> reduce(event, currentState)
            is DetailsViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: DetailsEvent, currentState: DetailsViewState.Closed) {
        when (event) {
            is DetailsEvent.EnterScreen -> enterScreen()
            else -> {}
        }
    }

    private fun reduce(event: DetailsEvent, currentState: DetailsViewState.Display) {
        when (event) {
            is DetailsEvent.OnAddToFavouritesClicked -> {
                onAddToFavouritesClicked(event.item)
            }
            else -> {}
        }
    }

    private fun enterScreen() {
        viewModelScope.launch {
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
            _state.update {
                DetailsViewState.Display
            }
        }
    }

    private fun exitScreen() {
        viewModelScope.launch {
            _state.update {
                DetailsViewState.Closed
            }
        }
    }

    fun setItem(item: ProductItem) {
        _item.update {
            item
        }
    }

    fun setImages(images: List<ImageBitmap>) {
        _images.update {
            images
        }
    }

    private fun onAddToFavouritesClicked(item: ProductItem) {
        viewModelScope.launch {
            var isExists = false
            _favourites.value.forEach { fav ->
                if (fav.id == item.id) {
                    deleteFromFavourites(item)
                    isExists = true
                }
            }
            if (!isExists) {
                addToFavourites(item)
            }
        }
    }

    private fun addToFavourites(item: ProductItem) {
        viewModelScope.launch {
            catalogRepository.addToFavourites(
                Favourites(
                    id = item.id,
                    item = item
                )
            )
            _favourites.update {
                catalogRepository.selectAllFavourites()
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