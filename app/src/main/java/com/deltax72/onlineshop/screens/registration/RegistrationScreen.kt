package com.deltax72.onlineshop.screens.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.navigation.events.NavigationEvent
import com.deltax72.onlineshop.screens.Screen
import com.deltax72.onlineshop.screens.registration.states.RegistrationViewState
import com.deltax72.onlineshop.screens.registration.views.RegistrationViewDisplay
import com.deltax72.onlineshop.utils.rememberLifecycleEvent

@Composable
fun RegistrationScreen(
    navigationViewModel: NavigationViewModel,
    viewModel: RegistrationViewModel
) {
    val state by viewModel.state.collectAsState()

    val authUserState by viewModel.authUserState.collectAsState()

    val lifecycleEvent = rememberLifecycleEvent()

    navigationViewModel.apply {
        onEvent(
            NavigationEvent.DisableBottomNavigationBar
        )
    }

    when (val currentState = state) {
        is RegistrationViewState.Display -> RegistrationViewDisplay(
            authUserState = authUserState,
            onFirstNameChanged = {
                viewModel.onEvent(RegistrationEvent.OnFirstNameChanged(it))
            },
            onLastNameChanged = {
                viewModel.onEvent(RegistrationEvent.OnLastNameChanged(it))
            },
            onPhoneNumberChanged = {
                viewModel.onEvent(RegistrationEvent.OnPhoneNumberChanged(it))
            },
            onClearFirstName = {
                viewModel.onEvent(RegistrationEvent.OnFirstNameChanged(""))
            },
            onClearLastName = {
                viewModel.onEvent(RegistrationEvent.OnLastNameChanged(""))
            },
            onClearPhoneNumber = {
                viewModel.onEvent(RegistrationEvent.OnPhoneNumberChanged(""))
            },
            onButtonClicked = {
                viewModel.onEvent(RegistrationEvent.OnButtonClicked)
                navigationViewModel.onEvent(NavigationEvent.NavigateToWithPopUp(
                    Screen.Catalog,
                    Screen.Catalog
                ))
            }
        )
        is RegistrationViewState.IsAuthenticated -> {
            navigationViewModel.onEvent(NavigationEvent.NavigateToWithPopUp(
                Screen.Catalog,
                Screen.Catalog
            ))
        }
        else -> {}
    }

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            Lifecycle.Event.ON_START -> {
                viewModel.onEvent(RegistrationEvent.EnterScreen)
            }
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onEvent(RegistrationEvent.Display)
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.onEvent(RegistrationEvent.ExitScreen)
            }
            else -> {}
        }
    }
}