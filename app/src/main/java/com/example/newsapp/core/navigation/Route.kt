package com.example.newsapp.core.navigation

private object RouteNames {

    const val onBoardingName = "onBoarding"
    const val searchScreenName = "search"
    const val homeScreenName = "home"
    const val bookMarksScreenName = "bookMarks"
    const val detailScreenName = "details"
    const val mainNavigation = "/"
    const val newsLayoutName = "newsLayout"
    const val newsLayoutScreenName = "/newsLayoutScreen"

}

sealed class Route(
    val route: String
) {
    data object OnBoardingScreen : Route(route = RouteNames.onBoardingName)
    data object SearchScreen : Route(route = RouteNames.searchScreenName)
    data object BookMarksScreen : Route(route = RouteNames.bookMarksScreenName)
    data object HomeScreen : Route(route = RouteNames.homeScreenName)
    data object DetailsScreen : Route(route = RouteNames.detailScreenName)
    data object MainNavigation : Route(route = RouteNames.mainNavigation)
    data object NewsLayout : Route(route = RouteNames.newsLayoutName)
    data object NewsLayoutScreen : Route(route = RouteNames.newsLayoutScreenName)

}