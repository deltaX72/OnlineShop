package com.deltax72.onlineshop.services.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.deltax72.onlineshop.AppSettings
import com.deltax72.onlineshop.AppSettingsSerializer
import com.deltax72.onlineshop.screens.registration.states.AuthUserState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by dataStore("settings.json", AppSettingsSerializer)

class DataStoreManager(val context: Context) {
    suspend fun saveUserData(
        firstName: String,
        lastName: String,
        phoneNumber: String
    ) {
        context.dataStore.updateData {
            it.copy(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            )
        }
    }

    suspend fun getUserData(): AuthUserState {
        with (context.dataStore.data.first()) {
            return AuthUserState(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            )
        }
    }

    suspend fun logOutUser() {
        context.dataStore.updateData {
            AppSettings()
        }
    }

    suspend fun isAuthenticated(): Boolean {
        with (context.dataStore.data.first()) {
            return firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()
        }
    }
}