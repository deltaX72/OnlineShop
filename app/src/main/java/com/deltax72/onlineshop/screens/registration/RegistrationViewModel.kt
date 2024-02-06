package com.deltax72.onlineshop.screens.registration

import androidx.lifecycle.viewModelScope
import com.deltax72.onlineshop.base.BaseViewModel
import com.deltax72.onlineshop.screens.registration.states.AuthUserState
import com.deltax72.onlineshop.screens.registration.states.RegistrationViewState
import com.deltax72.onlineshop.utils.EventHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository
): BaseViewModel<RegistrationEvent, RegistrationViewState>() {
    override val _state: MutableStateFlow<RegistrationViewState> =
        MutableStateFlow(RegistrationViewState.Closed)
    val state = _state.asStateFlow()

    private val _authUserState: MutableStateFlow<AuthUserState> =
        MutableStateFlow(AuthUserState())
    val authUserState = _authUserState.asStateFlow()

    override fun onEvent(event: RegistrationEvent) {
        when (val currentState = _state.value) {
            is RegistrationViewState.Closed -> reduce(event, currentState)
            is RegistrationViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: RegistrationEvent, currentState: RegistrationViewState.Closed) {
        when (event) {
            is RegistrationEvent.EnterScreen -> display()
            is RegistrationEvent.Display -> display()
            else -> {}
        }
    }

    private fun reduce(event: RegistrationEvent, currentState: RegistrationViewState.Display) {
        when (event) {
            is RegistrationEvent.ExitScreen -> exitScreen()
            is RegistrationEvent.OnFirstNameChanged -> onFirstNameChanged(event)
            is RegistrationEvent.OnLastNameChanged -> onLastNameChanged(event)
            is RegistrationEvent.OnPhoneNumberChanged -> onPhoneNumberChanged(event)
            is RegistrationEvent.OnButtonClicked -> onButtonClicked()
            else -> {}
        }
    }

    private fun display() {
        viewModelScope.launch {
            if (registrationRepository.isAuthenticated()) {
                _state.update {
                    RegistrationViewState.IsAuthenticated
                }
            } else {
                _state.update {
                    RegistrationViewState.Display
                }
            }
        }
    }

    private fun exitScreen() {
        viewModelScope.launch {
            _state.update {
                RegistrationViewState.Closed
            }
        }
    }

    private fun onFirstNameChanged(event: RegistrationEvent.OnFirstNameChanged) {
        viewModelScope.launch {
            with(event.value) {
                if (isEmpty()) {
                    _authUserState.update {
                        it.copy(
                            firstName = this,
                            isFirstNameCorrect = null
                        )
                    }
                    return@with
                }
                if (isContainsNotCyrillic(this)) {
                    _authUserState.update {
                        it.copy(
                            firstName = this,
                            isFirstNameCorrect = false
                        )
                    }
                } else {
                    _authUserState.update {
                        it.copy(
                            firstName = this,
                            isFirstNameCorrect = true
                        )
                    }
                }
            }
        }
    }

    private fun onLastNameChanged(event: RegistrationEvent.OnLastNameChanged) {
        viewModelScope.launch {
            with(event.value) {
                if (isEmpty()) {
                    _authUserState.update {
                        it.copy(
                            lastName = this,
                            isLastNameCorrect = null
                        )
                    }
                    return@with
                }
                if (isContainsNotCyrillic(this)) {
                    _authUserState.update {
                        it.copy(
                            lastName = this,
                            isLastNameCorrect = false
                        )
                    }
                } else {
                    _authUserState.update {
                        it.copy(
                            lastName = this,
                            isLastNameCorrect = true
                        )
                    }
                }
            }
        }
    }

    private fun onPhoneNumberChanged(event: RegistrationEvent.OnPhoneNumberChanged) {
        viewModelScope.launch {
            with (event.value) {
                val oldValue = _authUserState.value.phoneNumber
                if (oldValue.isEmpty()) {
                    if (first() == '7') {
                        _authUserState.update {
                            it.copy(
                                phoneNumber = digitsToPhoneNumber(phoneNumberToDigits(this)),
                                isPhoneNumberCorrect = false
                            )
                        }
                    } else {
                        _authUserState.update {
                            it.copy(
                                phoneNumber = digitsToPhoneNumber(phoneNumberToDigits("7$this")),
                                isPhoneNumberCorrect = false
                            )
                        }
                    }
                } else {
                    _authUserState.update {
                        it.copy(
                            phoneNumber = digitsToPhoneNumber(phoneNumberToDigits(this)),
                            isPhoneNumberCorrect = isPhoneNumberCorrect()
                        )
                    }
                }
            }
        }
    }

    private fun onButtonClicked() {
        viewModelScope.launch {
            with (_authUserState.value) {
                registrationRepository.saveUserData(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
            }
        }
    }

    private fun isContainsNotCyrillic(value: String): Boolean {
        if (value.isEmpty()) return false
        value.forEach {
            if (Character.UnicodeBlock.of(it) != Character.UnicodeBlock.CYRILLIC) {
                return true
            }
        }
        return false
    }

    private fun isPhoneNumberCorrect(): Boolean {
        return phoneNumberToDigits(_authUserState.value.phoneNumber).length == 10
    }

    // 79609006970
    // 7 960 900 69 70
    // + 7 960 900 69 70
    private fun digitsToPhoneNumber(number: String): String {
        return buildString {
            var spaces = 0
            var buffer = number

            val len = buffer.length

            if (len > 11) {
                buffer = buffer.substring(0, 11)
            }

            if (len == 0) {
                clear()
            } else {
                append(buffer)

                if (len > 1) {
                    insert(1, " ")
                    spaces++
                }
                if (len > 4) {
                    insert(4 + spaces, " ")
                    spaces++
                }
                if (len > 7) {
                    insert(7 + spaces, " ")
                    spaces++
                }
                if (len > 9) {
                    insert(9 + spaces, " ")
                }

                insert(0, "+ ")
            }
        }
    }

    private fun phoneNumberToDigits(number: String): String {
        return buildString {
            number.forEach {
                if (it.isDigit()) {
                    append(it)
                }
            }
        }
    }
}