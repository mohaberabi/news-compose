package com.example.newsapp.features.search.viewmodel

sealed class SearchEvent {


    data class SearchQueryChanged(
        val searchQuery: String
    ) : SearchEvent()

    data object SearchRequested : SearchEvent()
}