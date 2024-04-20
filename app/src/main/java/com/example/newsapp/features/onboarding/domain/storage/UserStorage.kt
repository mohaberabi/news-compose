package com.example.newsapp.features.onboarding.domain.storage

import kotlinx.coroutines.flow.Flow


interface UserStorage {


    suspend fun saveOnBoarding()
    fun readOnBoarding(): Flow<Boolean>
}