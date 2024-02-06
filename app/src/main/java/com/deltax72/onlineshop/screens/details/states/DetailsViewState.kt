package com.deltax72.onlineshop.screens.details.states

import com.deltax72.onlineshop.screens.registration.RegistrationEvent
import com.deltax72.onlineshop.screens.registration.states.RegistrationViewState

sealed class DetailsViewState {
    object Loading: DetailsViewState()

    object Display: DetailsViewState()

    object Closed: DetailsViewState()
}
