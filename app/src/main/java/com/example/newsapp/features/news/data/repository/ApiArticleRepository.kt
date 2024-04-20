package com.example.newsapp.features.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsapp.features.news.data.remote.api.NewsApi
import com.example.newsapp.features.news.data.remote.paging.ArticlePagingSource
import com.example.newsapp.features.news.data.remote.paging.SearchArticlesPagingSource
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.domain.repository.NewsRepository
import com.example.newsapp.features.news.mappers.toArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApiArticleRepository(
    private val api: NewsApi
) : NewsRepository {
    override fun getArticleResponse(): Flow<PagingData<Article>> {


        val pager = Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                ArticlePagingSource(
                    api = api,
                )
            }

        ).flow
        return pager.map { data ->
            data.map {
                it.toArticle()
            }
        }
    }

    override fun searchNews(
        query: String,

        ): Flow<PagingData<Article>> {

        val pager = Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchArticlesPagingSource(
                    newsApi = api,
                    searchQuery = query
                )
            }

        ).flow
        return pager.map { data ->
            data.map {
                it.toArticle()
            }
        }
    }

  
}