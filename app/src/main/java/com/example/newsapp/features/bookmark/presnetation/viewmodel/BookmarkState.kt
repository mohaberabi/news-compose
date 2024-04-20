package com.example.newsapp.features.bookmark.presnetation.viewmodel

import com.example.newsapp.features.news.domain.model.Article

data class BookmarkState(
    val bookmarks: List<Article> = emptyList()
)
