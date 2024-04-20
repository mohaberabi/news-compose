package com.example.newsapp.features.bookmark.domain.usecase

import com.example.newsapp.features.bookmark.domain.repository.BookMarkRepository
import com.example.newsapp.features.news.domain.model.Article

class GetArticleUseCase(private val bookMarkRepository: BookMarkRepository) {

    suspend operator fun invoke(url: String): Article? =
        bookMarkRepository.getArticle(url)
}