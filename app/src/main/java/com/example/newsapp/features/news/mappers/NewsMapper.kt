package com.example.newsapp.features.news.mappers

import com.example.newsapp.features.news.data.local.entity.ArticleLocal
import com.example.newsapp.features.news.data.local.entity.LocalSource
import com.example.newsapp.features.news.data.remote.dto.ArticleRemote
import com.example.newsapp.features.news.data.remote.dto.ArticleSourceRemote
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.domain.model.ArticleSource

fun Article.toLocal(): ArticleLocal {
    return ArticleLocal(
        author,
        content,
        description,
        publishedAt,
        source.toLocalSource(),
        title,
        url,
        urlToImage
    )
}


fun ArticleSource.toLocalSource(): LocalSource {
    return LocalSource(
        id, name
    )
}

fun ArticleRemote.toArticle(): Article {
    return Article(
        author ?: "",
        content ?: "",
        description ?: "",
        publishedAt ?: "",
        source.toArticleSource(),
        title,
        url,
        urlToImage
    )
}


fun ArticleSourceRemote.toArticleSource(): ArticleSource {
    return ArticleSource(id, name)
}