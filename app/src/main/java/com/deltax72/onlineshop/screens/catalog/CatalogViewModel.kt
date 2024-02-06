package com.deltax72.onlineshop.screens.catalog

import androidx.lifecycle.viewModelScope
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.Items
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.states.CatalogViewState
import com.deltax72.onlineshop.screens.catalog.states.SortType
import com.deltax72.onlineshop.screens.catalog.states.SortedItems
import com.deltax72.onlineshop.screens.catalog.states.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val catalogRepository: CatalogRepository
): BaseViewModel<CatalogEvent, CatalogViewState>() {
    override val _state: MutableStateFlow<CatalogViewState> =
        MutableStateFlow(CatalogViewState.Closed)
    val state = _state.asStateFlow()

    private val _items: MutableStateFlow<Items> =
        MutableStateFlow(Items())
    val items = _items.asStateFlow()

    private val _sortType: MutableStateFlow<SortType> =
        MutableStateFlow(SortType.None)
    private val _tag: MutableStateFlow<Tag> =
        MutableStateFlow(Tag.All())

    val sortedItems = combine(
        _items,
        _sortType,
        _tag
    ) { items, sortType, tag ->
        val filtered = Items(
            items = with (items.items) {
                when (sortType) {
                    is SortType.ByPopularity -> sortedByDescending {
                        it.feedback.rating
                    }
                    is SortType.ByPriceReduction -> sortedByDescending {
                        it.price.priceWithDiscount.toInt()
                    }
                    is SortType.ByPriceIncrease -> sortedBy {
                        it.price.priceWithDiscount.toInt()
                    }
                    else -> this
                }
                    .takeIf {
                        tag.name == Tag.All().name
                    } ?: filter {
                        it.tags.contains(tag.key)
                    }
            }
        )

        SortedItems(
            items = filtered,
            sortType = sortType,
            tag = tag
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SortedItems())

    private val _favourites: MutableStateFlow<List<Favourites>> =
        MutableStateFlow(emptyList())
    val favourites = _favourites.asStateFlow()

    override fun onEvent(event: CatalogEvent) {
        when (val currentState = _state.value) {
            is CatalogViewState.Closed -> reduce(event, currentState)
            is CatalogViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: CatalogEvent, currentState: CatalogViewState.Closed) {
        when (event) {
            is CatalogEvent.EnterScreen -> enterScreen()
            else -> {}
        }
    }

    private fun reduce(event: CatalogEvent, currentState: CatalogViewState.Display) {
        when (event) {
            is CatalogEvent.SortByPopularity -> {
                _sortType.update {
                    SortType.ByPopularity
                }
            }
            is CatalogEvent.SortByPriceReduction -> {
                _sortType.update {
                    SortType.ByPriceReduction
                }
            }
            is CatalogEvent.SortByPriceIncrease -> {
                _sortType.update {
                    SortType.ByPriceIncrease
                }
            }
            is CatalogEvent.OnTagSelected -> {
                _tag.update {
                    event.tag
                }
            }
            is CatalogEvent.OnAddToFavouritesClicked -> {
                onAddToFavouritesClicked(event.item)
            }
            else -> {}
        }
    }

    private fun enterScreen() {
        viewModelScope.launch {
            _items.update {
                catalogRepository.getItems()
            }
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
            _state.update {
                CatalogViewState.Display
            }
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

    private fun selectAllFavourites() {
        viewModelScope.launch {
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
        }
    }
}