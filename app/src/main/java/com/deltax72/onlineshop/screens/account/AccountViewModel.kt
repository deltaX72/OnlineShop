package com.deltax72.onlineshop.screens.account

import androidx.lifecycle.viewModelScope
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.screens.account.states.AccountViewState
import com.deltax72.onlineshop.screens.catalog.CatalogRepository
import com.deltax72.onlineshop.screens.registration.states.AuthUserState
import com.deltax72.onlineshop.services.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val catalogRepository: CatalogRepository,
    private val dataStoreManager: DataStoreManager
): BaseViewModel<AccountEvent, AccountViewState>() {
    override val _state: MutableStateFlow<AccountViewState> =
        MutableStateFlow(AccountViewState.Closed)
    val state = _state.asStateFlow()

    private val _favourites: MutableStateFlow<List<Favourites>> =
        MutableStateFlow(emptyList())
    val favourites = _favourites.asStateFlow()

    private val _authUserState: MutableStateFlow<AuthUserState> =
        MutableStateFlow(AuthUserState())
    val authUserState = _authUserState.asStateFlow()

    override fun onEvent(event: AccountEvent) {
        when (val currentState = _state.value) {
            is AccountViewState.Closed -> reduce(event, currentState)
            is AccountViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: AccountEvent, currentState: AccountViewState.Closed) {
        when (event) {
            is AccountEvent.EnterScreen -> enterScreen()
            else -> {}
        }
    }

    private fun reduce(event: AccountEvent, currentState: AccountViewState.Display) {
        when (event) {
            is AccountEvent.ExitScreen -> exitScreen()
            else -> {}
        }
    }

    private fun enterScreen() {
        viewModelScope.launch {
            _favourites.update {
                catalogRepository.selectAllFavourites()
            }
            _authUserState.update {
                dataStoreManager.getUserData()
            }
            _state.update {
                AccountViewState.Display
            }
        }
    }

    private fun exitScreen() {
        viewModelScope.launch {
            _state.update {
                AccountViewState.Closed
            }
        }
    }
}