package com.example.newsapp.features.onboarding.domain.usecase

import com.example.newsapp.features.onboarding.domain.storage.UserStorage
import kotlinx.coroutines.flow.Flow

class GetOnBoardingUseCase(
    private val userStorage: UserStorage
) {

    operator fun invoke(): Flow<Boolean> = userStorage.readOnBoarding()
}