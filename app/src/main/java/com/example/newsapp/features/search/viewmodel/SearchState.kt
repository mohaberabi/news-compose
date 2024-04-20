package com.example.newsapp.features.search.viewmodel

import androidx.paging.PagingData
import com.example.newsapp.features.news.data.remote.paging.ArticlePagingSource
import com.example.newsapp.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val query: String = "",
    val news: Flow<PagingData<Article>>? = null,
)
