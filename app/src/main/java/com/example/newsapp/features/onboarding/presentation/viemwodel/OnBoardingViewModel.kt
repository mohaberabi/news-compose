package com.example.newsapp.features.onboarding.presentation.viemwodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.features.onboarding.domain.usecase.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: OnBoardingUseCases,
) : ViewModel() {


    fun onEvent(event: OnBoardingEvent) {
        if (event is OnBoardingEvent.SaveOnBoarding) {

            saveOnBoarding()
        }

    }

    private fun saveOnBoarding() = viewModelScope.launch {
        useCases.saveOnboardingUseCase()
    }
}