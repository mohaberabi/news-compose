package com.example.newsapp.features.onboarding.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.core.composables.Indicator
import com.example.newsapp.core.composables.PrimaryButton
import com.example.newsapp.core.composables.PrimaryTextButton
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.onboarding.presentation.composbales.OnBoardingContainer
import com.example.newsapp.features.onboarding.presentation.composbales.onBoardingSteps
import com.example.newsapp.features.onboarding.presentation.viemwodel.OnBoardingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable

fun OnBoardingScreen(
    onEvent: (OnBoardingEvent) -> Unit = {},

    ) {
    val scope = rememberCoroutineScope()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            val pagerState = rememberPagerState(initialPage = 0) {
                onBoardingSteps.size
            }


            HorizontalPager(state = pagerState) { index ->
                OnBoardingContainer(step = onBoardingSteps[index])

            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.md)
                    .navigationBarsPadding()
            ) {

                Indicator(
                    size = onBoardingSteps.size,
                    selectedIndex = pagerState.currentPage
                )
                Row {

                    if (pagerState.currentPage >= 1)
                        PrimaryTextButton(label = "Back",
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)

                                }
                            }
                        )

                    PrimaryButton(
                        label = if (pagerState.currentPage == 2) "Get Started" else "Next"
                    ) {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                onEvent(OnBoardingEvent.SaveOnBoarding)
                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                        }
                    }
                }
            }


        }

    }

}