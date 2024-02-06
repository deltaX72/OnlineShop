package com.deltax72.onlineshop.screens.catalog

import com.deltax72.onlineshop.data.entities.Items
import com.deltax72.onlineshop.data.entities.ProductItem
import retrofit2.http.GET

interface CatalogRemoteDataSource {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010/")
    suspend fun getItems(): Items
}