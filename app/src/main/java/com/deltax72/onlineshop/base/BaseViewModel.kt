package com.deltax72.onlineshop.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.deltax72.onlineshop.appModule
import com.deltax72.onlineshop.utils.EventHandler
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<E, S>: ViewModel(), EventHandler<E> {
    val savedStateHandle: SavedStateHandle = appModule.savedStateHandle
    protected abstract val _state: MutableStateFlow<S>
}