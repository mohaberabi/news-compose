package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.core.navigation.NewsNavGraph
import com.example.newsapp.features.onboarding.presentation.screens.OnBoardingScreen
import com.example.newsapp.core.theme.NewsAppTheme
import com.example.newsapp.core.viewmodel.MainViewModel
import com.example.newsapp.features.onboarding.presentation.viemwodel.OnBoardingViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val state = mainViewModel.state.collectAsState()
            NewsAppTheme {

                val isDark = isSystemInDarkTheme()
                val uiController = rememberSystemUiController()
                SideEffect {
                    uiController.setStatusBarColor(
                        Color.Transparent,
                        darkIcons = !isDark
                    )
                }
                NewsNavGraph(initRoute = state.value.initialRoute)
            }
        }
    }
}

