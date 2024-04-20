package com.example.newsapp.features.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.features.news.domain.model.ArticleSource

@Entity("news")

data class ArticleLocal(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: LocalSource,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
)


data class LocalSource(
    val id: String,
    val name: String
)