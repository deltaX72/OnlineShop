package com.deltax72.onlineshop.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule: NetworkModule by lazy(::NetworkModuleImpl)

interface NetworkModule {
    val baseUrl: String

    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val moshi: Moshi
    val retrofit: Retrofit
}

class NetworkModuleImpl: NetworkModule {
    override val baseUrl: String by lazy {
        "https://run.mocky.io/"
    }

    override val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    override val okHttpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    override val moshi: Moshi by lazy {
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    override val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi).asLenient()
            )
            .baseUrl(baseUrl)
            .build()
    }
}