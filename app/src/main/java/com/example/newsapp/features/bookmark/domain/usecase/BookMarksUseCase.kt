package com.example.newsapp.features.bookmark.domain.usecase

data class BookMarksUseCase(
    val addToBookMarkUseCase: AddToBookMarkUseCase,
    val removeFromBookMarkUseCase: RemoveFromBookMarkUseCase,
    val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    val getArticleUseCase: GetArticleUseCase

)