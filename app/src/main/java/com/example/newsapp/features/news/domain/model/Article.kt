package com.example.newsapp.features.news.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Article(

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable