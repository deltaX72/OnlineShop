package com.deltax72.onlineshop.screens.registration.states

data class AuthUserState(
    val firstName: String = "",
    val isFirstNameCorrect: Boolean? = null,
    val lastName: String = "",
    val isLastNameCorrect: Boolean? = null,
    val phoneNumber: String = "",
    val numberDigits: String = "",
    val isPhoneNumberCorrect: Boolean? = null,
    val isButtonActive: Boolean = false
)
