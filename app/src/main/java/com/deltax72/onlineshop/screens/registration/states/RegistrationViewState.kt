package com.deltax72.onlineshop.screens.registration.states

sealed class RegistrationViewState {
    object Loading: RegistrationViewState()

    object Display: RegistrationViewState()

    object Closed: RegistrationViewState()

    object IsAuthenticated: RegistrationViewState()
}