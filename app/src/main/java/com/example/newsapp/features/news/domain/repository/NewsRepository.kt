package com.example.newsapp.features.news.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticleResponse(
    ): Flow<PagingData<Article>>

    fun searchNews(
        query: String,
    ): Flow<PagingData<Article>>


}