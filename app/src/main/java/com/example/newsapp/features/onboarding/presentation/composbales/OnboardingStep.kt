package com.example.newsapp.features.onboarding.presentation.composbales

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class OnboardingStep(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,

    )


val onBoardingSteps = listOf<OnboardingStep>(


    OnboardingStep(
        "Lorem ipsum is placeholder text commonly used in the graphic, ",
        "Lorem ipsum is placeholder text commonly used in the graphic,",
        R.drawable.onboarding1
    ),
    OnboardingStep(
        "Lorem ipsum is placeholder text commonly used in the graphic, ",
        "Lorem ipsum is placeholder text commonly used in the graphic,",
        R.drawable.onboarding2
    ),
    OnboardingStep(
        "Lorem ipsum is placeholder text commonly used in the graphic, ",
        "Lorem ipsum is placeholder text commonly used in the graphic,", R.drawable.onboarding3
    )
)