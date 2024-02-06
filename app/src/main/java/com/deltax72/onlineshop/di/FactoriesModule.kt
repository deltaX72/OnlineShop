package com.deltax72.onlineshop.di

import androidx.lifecycle.ViewModelProvider
import com.deltax72.onlineshop.appModule
import com.deltax72.onlineshop.navigation.NavigationViewModel
import com.deltax72.onlineshop.screens.account.AccountViewModel
import com.deltax72.onlineshop.screens.catalog.CatalogViewModel
import com.deltax72.onlineshop.screens.details.DetailsViewModel
import com.deltax72.onlineshop.screens.favourites.FavouritesViewModel
import com.deltax72.onlineshop.screens.registration.RegistrationViewModel
import com.deltax72.onlineshop.utils.viewModelFactory

val factoriesModule: FactoriesModule by lazy(::FactoriesModuleImpl)

interface FactoriesModule {
    val navigation: ViewModelProvider.Factory
    val registration: ViewModelProvider.Factory
    val catalog: ViewModelProvider.Factory
    val details: ViewModelProvider.Factory
    val account: ViewModelProvider.Factory
    val favourites: ViewModelProvider.Factory
}

class FactoriesModuleImpl: FactoriesModule {
    override val navigation: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            NavigationViewModel(
                dataStoreManager = appModule.dataStoreManager
            )
        }
    }
    override val registration: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            RegistrationViewModel(
                registrationRepository = repositoriesModule.registration
            )
        }
    }
    override val catalog: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            CatalogViewModel(
                catalogRepository = repositoriesModule.catalog
            )
        }
    }
    override val details: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            DetailsViewModel(
                catalogRepository = repositoriesModule.catalog
            )
        }
    }
    override val account: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            AccountViewModel(
                catalogRepository = repositoriesModule.catalog,
                dataStoreManager = appModule.dataStoreManager
            )
        }
    }
    override val favourites: ViewModelProvider.Factory by lazy {
        viewModelFactory {
            FavouritesViewModel(
                catalogRepository = repositoriesModule.catalog
            )
        }
    }
}