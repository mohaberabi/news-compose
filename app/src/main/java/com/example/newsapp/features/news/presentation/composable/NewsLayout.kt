package com.example.newsapp.features.news.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.core.navigation.Route
import com.example.newsapp.features.bookmark.presnetation.screen.BookMarksScreen
import com.example.newsapp.features.bookmark.presnetation.viewmodel.BookMarkViewModel
import com.example.newsapp.features.details.presentation.screens.DetailsScreen
import com.example.newsapp.features.details.presentation.viewmodel.DetailsViewModel
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.presentation.screens.NewsScreen
import com.example.newsapp.features.news.presentation.viewmodel.ArticlesViewModel
import com.example.newsapp.features.search.screen.SearchScreen
import com.example.newsapp.features.search.viewmodel.SearchViewModel

val bottomNavItems = listOf<BottomNavData>(
    BottomNavData(Icons.Default.Home, "Home"),
    BottomNavData(Icons.Default.Search, "Search"),
    BottomNavData(Icons.Default.List, "Bookmark"),
)

@Composable
fun NewsLayout() {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {

        mutableIntStateOf(0)


    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookMarksScreen.route -> 2
        else -> 0
    }


    val showBottomNavBar = remember(key1 = backStackState) {

        backStackState?.destination?.route != Route.DetailsScreen.route

    }

    Scaffold(bottomBar = {
        if (showBottomNavBar) NewsBottomNavBar(
            onSelect = { index ->

                when (index) {
                    0 -> navigateToTab(navController, Route.HomeScreen.route)
                    1 -> navigateToTab(navController, Route.SearchScreen.route)
                    2 -> navigateToTab(navController, Route.BookMarksScreen.route)

                }
            },
            selected = selectedItem,
            items = bottomNavItems
        )
    }) {
        NavHost(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {

            composable(Route.HomeScreen.route) {
                val viewModel: ArticlesViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                NewsScreen(
                    articles = articles,
                    onGoToSearch = {

                        navigateToTab(navController, Route.SearchScreen.route)
                    },
                    onGoToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }
            composable(Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState().value
                SearchScreen(
                    state = state,
                    onGoToDetails = { art ->
                        navigateToDetails(navController, art)
                    },
                    event = viewModel::onEvent
                )
            }
            composable(Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { art ->
                        DetailsScreen(
                            article = art,
                            navigateUp = { navController.navigateUp() },
                            event = viewModel::onEvent
                        )

                    }

            }
            composable(Route.BookMarksScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()

                val state = viewModel.state.collectAsState()
                BookMarksScreen(state = state.value,
                    onGoToDetails = {
                        navigateToDetails(navController, it)
                    }
                )
            }
        }


    }

}

private fun navigateToTab(
    navController: NavController,
    route: String
) {

    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true

        }
    }

}

private fun navigateToDetails(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}