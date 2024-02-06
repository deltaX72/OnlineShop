package com.deltax72.onlineshop.di

import com.deltax72.onlineshop.screens.catalog.CatalogRemoteDataSource

val remoteDataSourceModule: RemoteDataSourceModule by lazy(::RemoteDataSourceModuleImpl)

interface RemoteDataSourceModule {
    val catalog: CatalogRemoteDataSource
}

class RemoteDataSourceModuleImpl : RemoteDataSourceModule {
    override val catalog: CatalogRemoteDataSource by lazy {
        networkModule.retrofit.create(CatalogRemoteDataSource::class.java)
    }
}