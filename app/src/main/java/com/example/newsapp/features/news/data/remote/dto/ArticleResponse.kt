package com.example.newsapp.features.news.data.remote.dto


data class ArticleResponse(
    val articles: List<ArticleRemote>,
    val status: String,
    val totalResults: Int
)