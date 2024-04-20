package com.example.newsapp.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.example.newsapp.features.news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases,
) : ViewModel() {


    private val _state = MutableStateFlow(SearchState())

    val state: StateFlow<SearchState>
        get() = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchRequested -> {
                searchNews()
            }

            is SearchEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(query = event.searchQuery)
                }
            }

        }
    }

    private fun searchNews() {
        val articles = newsUseCase
            .searchNewsUseCase(_state.value.query)
            .cachedIn(viewModelScope)

        _state.update {
            it.copy(news = articles)
        }

    }
}