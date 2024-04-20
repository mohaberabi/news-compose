package com.example.newsapp.features.onboarding.domain.usecase

import com.example.newsapp.features.onboarding.domain.storage.UserStorage

class SaveOnboardingUseCase(
    private val userStorage:
    UserStorage
) {


    suspend operator fun invoke() {
        userStorage.saveOnBoarding()
    }

}