package com.example.newsapp.features.bookmark.domain.repository

import com.example.newsapp.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {

    suspend fun getArticle(url: String): Article?

    suspend fun deleteArticle(article: Article)
    fun getBookMarks(): Flow<List<Article>>
    suspend fun addToBookmark(article: Article)
}