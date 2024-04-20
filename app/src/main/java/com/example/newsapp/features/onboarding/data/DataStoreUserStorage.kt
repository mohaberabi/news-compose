package com.example.newsapp.features.onboarding.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.features.onboarding.data.UserStorageKeys.userStorageDataStore
import com.example.newsapp.features.onboarding.domain.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserStorageKeys {
    const val userStorageDataStore = "userStorageDataStore"
    const val seenOnboardingKey = "seenOnboardingKey"

    val seenOnboarding = booleanPreferencesKey(seenOnboardingKey)
}

class DataStoreUserStorage(
    private val context: Context
) : UserStorage {

    override suspend fun saveOnBoarding() {

        context.dataStore.edit { settings ->
            settings[UserStorageKeys.seenOnboarding] = true
        }
    }

    override fun readOnBoarding(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->

            prefs[UserStorageKeys.seenOnboarding] ?: false
        }
    }

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(userStorageDataStore)
