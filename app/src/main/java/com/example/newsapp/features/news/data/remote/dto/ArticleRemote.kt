package com.example.newsapp.features.news.data.remote.dto


data class ArticleRemote(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: ArticleSourceRemote,
    val title: String,
    val url: String,
    val urlToImage: String
)