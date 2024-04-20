package com.example.newsapp.features.details.presentation.viewmodel

import com.example.newsapp.features.news.domain.model.Article

sealed class ArticleDetailsEvent {

    data object OnSaveArticle : ArticleDetailsEvent()
    data class UpsertDeleteArticle(val article: Article) : ArticleDetailsEvent()

}