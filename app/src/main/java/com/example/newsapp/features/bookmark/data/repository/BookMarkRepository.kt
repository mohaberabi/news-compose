package com.example.newsapp.features.bookmark.data.repository

import com.example.newsapp.features.bookmark.domain.repository.BookMarkRepository
import com.example.newsapp.features.news.data.local.daos.NewsDao
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.mappers.toArticle
import com.example.newsapp.features.news.mappers.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookMarkRepositoryImpl(
    private val newsDao: NewsDao
) : BookMarkRepository {
    override suspend fun getArticle(url: String): Article? {
        val local = newsDao.getArticle(url)
        return local?.toArticle()
    }

    override suspend fun deleteArticle(article: Article) = newsDao.deleteArticle(article.toLocal())


    override fun getBookMarks(): Flow<List<Article>> = newsDao.getArticles().map { items ->
        items.map { item ->
            item.toArticle()
        }
    }

    override suspend fun addToBookmark(article: Article) = newsDao.upsertArticle(article.toLocal())
}