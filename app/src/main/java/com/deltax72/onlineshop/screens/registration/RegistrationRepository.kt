package com.deltax72.onlineshop.screens.registration

import com.deltax72.onlineshop.base.BaseRepository
import com.deltax72.onlineshop.services.datastore.DataStoreManager

class RegistrationRepository(
    private val dataStoreManager: DataStoreManager,
): BaseRepository() {

    suspend fun saveUserData(
        firstName: String,
        lastName: String,
        phoneNumber: String
    ) {
        dataStoreManager.saveUserData(firstName, lastName, phoneNumber)
    }

    suspend fun isAuthenticated(): Boolean {
        return dataStoreManager.isAuthenticated()
    }
}