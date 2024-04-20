package com.example.newsapp.features.news.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.features.news.data.local.entity.ArticleLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: ArticleLocal)


    @Delete
    suspend fun deleteArticle(article: ArticleLocal)


    @Query("SELECT * FROM news")
    fun getArticles(): Flow<List<ArticleLocal>>

    @Query("SELECT * FROM news WHERE url=:url")
    suspend fun getArticle(url: String): ArticleLocal?
}