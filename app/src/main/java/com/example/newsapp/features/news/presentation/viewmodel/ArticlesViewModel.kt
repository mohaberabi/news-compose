package com.example.newsapp.features.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.features.news.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    val news = newsUseCases.getNewsUseCases(
    ).cachedIn(viewModelScope)

}