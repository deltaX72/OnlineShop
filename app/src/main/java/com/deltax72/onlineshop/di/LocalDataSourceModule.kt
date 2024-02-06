package com.deltax72.onlineshop.di

import com.deltax72.onlineshop.appModule
import com.deltax72.onlineshop.screens.catalog.CatalogLocalDataSource

val localDataSourceModule: LocalDataSourceModule by lazy(::LocalDataSourceModuleImpl)

interface LocalDataSourceModule {
    val catalog: CatalogLocalDataSource
}

class LocalDataSourceModuleImpl: LocalDataSourceModule {
    override val catalog: CatalogLocalDataSource by lazy {
        appModule.database.catalogDao()
    }
}