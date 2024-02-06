package com.deltax72.onlineshop.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface EventHandler <E> {
    fun onEvent(event: E)
}