package com.deltax72.onlineshop.navigation

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.navigation.states.BottomNavigationState
import com.deltax72.onlineshop.navigation.states.NavigationBarActiveState
import com.deltax72.onlineshop.navigation.states.NavigationBarItem
import com.deltax72.onlineshop.navigation.states.NavigationBarItems
import com.deltax72.onlineshop.navigation.states.NavigationViewState
import com.deltax72.onlineshop.screens.Screen
import com.deltax72.onlineshop.services.datastore.DataStoreManager
import com.deltax72.onlineshop.utils.EventHandler
import com.deltax72.onlineshop.utils.navigateWithPopUp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigationViewModel(
    private val dataStoreManager: DataStoreManager
): BaseViewModel<NavigationEvent, NavigationViewState>(){
    override val _state: MutableStateFlow<NavigationViewState> =
        MutableStateFlow(NavigationViewState.Closed)
    val state = _state.asStateFlow()

    private val _rootNavController: MutableStateFlow<NavHostController?> =
        MutableStateFlow(null)

    private val _bottomActiveState: MutableStateFlow<NavigationBarActiveState> =
        MutableStateFlow(NavigationBarActiveState.Disabled)
    private val _bottomItemsState: MutableStateFlow<NavigationBarItems> =
        MutableStateFlow(NavigationBarItems.None)
    private val _bottomSelectedItemState: MutableStateFlow<NavigationBarItem> =
        MutableStateFlow(NavigationBarItem.None)

    val bottomState = combine(
        _bottomActiveState,
        _bottomItemsState,
        _bottomSelectedItemState
    ) { active, items, selected ->
        BottomNavigationState(
            activeState = active,
            items = items,
            selectedItem = selected
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BottomNavigationState())

    override fun onEvent(event: NavigationEvent) {
        when (val currentState = _state.value) {
            is NavigationViewState.Display -> reduce(event, currentState)
            is NavigationViewState.Closed -> reduce(event, currentState)
            else -> {}
        }
    }

    fun onEvent(vararg events: NavigationEvent) {
        events.forEach { event ->
            onEvent(event)
        }
    }

    private fun reduce(event: NavigationEvent, currentState: NavigationViewState.Closed) {
        when (event) {
            is NavigationEvent.EnterNavigation -> enterNavigation(event)
            else -> {}
        }
    }

    private fun reduce(event: NavigationEvent, currentState: NavigationViewState.Display) {
        when (event) {
            is NavigationEvent.NavigateTo -> navigateTo(event.screen)
            is NavigationEvent.NavigateToWithPopUp -> navigateToWithPopup(event)
            is NavigationEvent.NavigateToBottomNavigationItem -> navigateToBottomNavigationItem(event)
            is NavigationEvent.PopBackStack -> popBackStack()

            is NavigationEvent.EnableBottomNavigationBar -> enableBottomNavigationBar()
            is NavigationEvent.DisableBottomNavigationBar -> disableBottomNavigationBar()

            is NavigationEvent.UpdateBottomNavigationBar -> updateBottomNavigationBar(event)

            is NavigationEvent.LogOut -> logOut()

            else -> {}
        }
    }

    private fun enterNavigation(event: NavigationEvent.EnterNavigation) {
        viewModelScope.launch {
            _rootNavController.update {
                event.rootNavController
            }
            _state.update {
                NavigationViewState.Display
            }
        }
    }

    private fun navigateTo(screen: Screen) {
        _rootNavController.value?.navigate(screen.route)
    }

    private fun navigateToWithPopup(event: NavigationEvent.NavigateToWithPopUp) {
        _rootNavController.value?.navigateWithPopUp(
            navigateTo = event.navigateTo.route,
            popUpTo = event.popUpTo.route,
            saveState = true
        )
    }

    private fun enableBottomNavigationBar() {
        _bottomActiveState.update {
            NavigationBarActiveState.Enabled
        }
    }

    private fun disableBottomNavigationBar() {
        _bottomActiveState.update {
            NavigationBarActiveState.Disabled
        }
    }

    private fun updateBottomNavigationBar(event: NavigationEvent.UpdateBottomNavigationBar) {
        _bottomItemsState.update {
            event.items
        }
        _bottomSelectedItemState.update {
            event.selectedItem
        }
    }

    private fun navigateToBottomNavigationItem(event: NavigationEvent.NavigateToBottomNavigationItem) {
        with (_rootNavController.value!!) {
            navigate(event.navigateTo.route) {
                graph.findStartDestination().id.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    private fun popBackStack() {
        with (_rootNavController.value!!) {
            popBackStack()
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            dataStoreManager.logOutUser()
            _state.update {
                NavigationViewState.Exit
            }
        }
    }
}