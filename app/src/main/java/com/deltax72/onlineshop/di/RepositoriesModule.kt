package com.deltax72.onlineshop.di

import com.deltax72.onlineshop.appModule
import com.deltax72.onlineshop.screens.catalog.CatalogRepository
import com.deltax72.onlineshop.screens.registration.RegistrationRepository

val repositoriesModule: RepositoriesModule by lazy(::RepositoriesModuleImpl)

interface RepositoriesModule {
    val registration: RegistrationRepository
    val catalog: CatalogRepository
}

class RepositoriesModuleImpl: RepositoriesModule {
    override val registration: RegistrationRepository by lazy {
        RegistrationRepository(
            dataStoreManager = appModule.dataStoreManager,
        )
    }

    override val catalog: CatalogRepository by lazy {
        CatalogRepository(
            localDataSource = localDataSourceModule.catalog,
            remoteDataSource = remoteDataSourceModule.catalog
        )
    }
}