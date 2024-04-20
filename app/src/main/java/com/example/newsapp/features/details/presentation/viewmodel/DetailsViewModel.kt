package com.example.newsapp.features.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.util.UiText
import com.example.newsapp.features.bookmark.domain.repository.BookMarkRepository
import com.example.newsapp.features.bookmark.domain.usecase.BookMarksUseCase
import com.example.newsapp.features.bookmark.domain.usecase.RemoveFromBookMarkUseCase
import com.example.newsapp.features.news.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val bookMarkUseCase: BookMarksUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(DetailsState())

    val state: StateFlow<DetailsState>
        get() = _state


    fun onEvent(event: ArticleDetailsEvent) {
        when (event) {

            is ArticleDetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = bookMarkUseCase.getArticleUseCase(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {

                        deleteArticle(event.article)
                    }

                }
                _state.update {
                    it.copy(uiText = UiText.Empty)
                }
            }

            else -> Unit
        }
    }

    private fun upsertArticle(article: Article) {

        viewModelScope.launch {
            bookMarkUseCase.addToBookMarkUseCase(article)
        }


        _state.update {
            it.copy(uiText = UiText.DynamicText("Article Inserted"))
        }
    }

    private fun deleteArticle(article: Article) {

        viewModelScope.launch {
            bookMarkUseCase.removeFromBookMarkUseCase(article)
        }
        _state.update {
            it.copy(uiText = UiText.DynamicText("Article Deleted"))
        }
    }

}