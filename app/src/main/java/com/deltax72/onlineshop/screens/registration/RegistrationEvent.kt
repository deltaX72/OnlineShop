package com.deltax72.onlineshop.screens.registration

sealed class RegistrationEvent {
    object EnterScreen: RegistrationEvent()

    object Display: RegistrationEvent()

    object ExitScreen: RegistrationEvent()

    data class OnFirstNameChanged(val value: String): RegistrationEvent()
    data class OnLastNameChanged(val value: String): RegistrationEvent()
    data class OnPhoneNumberChanged(val value: String): RegistrationEvent()

    object OnButtonClicked: RegistrationEvent()
}
