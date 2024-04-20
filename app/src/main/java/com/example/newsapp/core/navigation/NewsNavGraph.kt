package com.example.newsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.features.news.presentation.composable.NewsLayout
import com.example.newsapp.features.news.presentation.screens.NewsScreen
import com.example.newsapp.features.news.presentation.viewmodel.ArticlesViewModel
import com.example.newsapp.features.onboarding.presentation.screens.OnBoardingScreen
import com.example.newsapp.features.onboarding.presentation.viemwodel.OnBoardingViewModel
import com.example.newsapp.features.search.screen.SearchScreen
import com.example.newsapp.features.search.viewmodel.SearchViewModel


@Composable
fun NewsNavGraph(
    initRoute: String
) {
    val controller = rememberNavController()


    NavHost(
        navController = controller,
        startDestination = initRoute
    ) {

        navigation(
            route = Route.MainNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {

            composable(route = Route.OnBoardingScreen.route) {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    onEvent = onBoardingViewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsLayout.route,
            startDestination = Route.NewsLayoutScreen.route
        ) {


            composable(Route.NewsLayoutScreen.route) {
                NewsLayout()
            }
        }
    }
}