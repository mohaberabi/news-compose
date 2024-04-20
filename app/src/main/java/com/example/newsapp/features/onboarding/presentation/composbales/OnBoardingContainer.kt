package com.example.newsapp.features.onboarding.presentation.composbales

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.R
import com.example.newsapp.core.util.Dimens


@Composable

fun OnBoardingContainer(
    modifier: Modifier = Modifier,
    step: OnboardingStep,
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = step.image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(Dimens.md))


        Text(
            text = step.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.textDisplay),
            modifier = Modifier.padding(Dimens.lg)
        )
        Text(
            text = step.description,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.textDisplay),
            modifier = Modifier.padding(Dimens.lg)
        )
    }
}