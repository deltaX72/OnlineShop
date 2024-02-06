package com.deltax72.onlineshop.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.deltax72.onlineshop.services.datastore.DataStoreManager

interface AppModule {
    val savedStateHandle: SavedStateHandle
    val dataStoreManager: DataStoreManager

    val database: AppDatabase
}

class AppModuleImpl(
    private val context: Context
): AppModule {

    override val savedStateHandle: SavedStateHandle by lazy {
        SavedStateHandle()
    }

    override val dataStoreManager: DataStoreManager by lazy {
        DataStoreManager(context = context)
    }

    override val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }
}