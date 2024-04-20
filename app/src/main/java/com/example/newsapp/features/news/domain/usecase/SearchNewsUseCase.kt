package com.example.newsapp.features.news.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(private val newsRepository: NewsRepository) {


    operator fun invoke(query: String): Flow<PagingData<Article>> =
        newsRepository.searchNews(query)
}