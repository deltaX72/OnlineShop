package com.deltax72.onlineshop.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.screens.account.AccountViewModel
import com.deltax72.onlineshop.screens.catalog.CatalogViewModel
import com.deltax72.onlineshop.screens.details.DetailsViewModel
import com.deltax72.onlineshop.screens.favourites.FavouritesViewModel
import com.deltax72.onlineshop.screens.registration.RegistrationViewModel

val viewModelsModule: ViewModelsModule by lazy(::ViewModelsModule)

class ViewModelsModule {
    val navigation: NavigationViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.navigation)

    val registration: RegistrationViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.registration)

    val catalog: CatalogViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.catalog)

    val details: DetailsViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.details)

    val account: AccountViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.account)

    val favourites: FavouritesViewModel
        @Composable
        get() = viewModel(factory = factoriesModule.favourites)
}