package com.example.newsapp.features.bookmark.domain.usecase

import com.example.newsapp.features.bookmark.domain.repository.BookMarkRepository
import com.example.newsapp.features.news.domain.model.Article

class GetAllBookmarksUseCase(
    private val bookMarkRepository: BookMarkRepository
) {
    operator fun invoke() = bookMarkRepository.getBookMarks()

}