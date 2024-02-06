package com.deltax72.onlineshop.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
fun <VM: ViewModel> viewModelFactory(initializer: () -> VM): ViewModelProvider.Factory =
    object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }

@Composable
inline fun <reified VM: ViewModel> createViewModel(
    initializer: VM
) = viewModel<VM>(
    factory = viewModelFactory {
        initializer
    }
)

@Composable
inline fun <reified VM: ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
    initializer: VM
): VM {
    val navGraphRoute = destination.parent?.route ?: return viewModel(
        factory = viewModelFactory {
            initializer
        }
    )
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(
        parentEntry,
        factory = viewModelFactory {
            initializer
        }
    )
}

@Composable
inline fun <reified VM: ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
    factory: ViewModelProvider.Factory
): VM {
    val navGraphRoute = destination.parent?.route ?: return viewModel(
        factory = factory
    )
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(
        parentEntry,
        factory = factory
    )
}