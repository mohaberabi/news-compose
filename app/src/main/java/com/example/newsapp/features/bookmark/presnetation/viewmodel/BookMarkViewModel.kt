package com.example.newsapp.features.bookmark.presnetation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.features.bookmark.domain.usecase.BookMarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val bookMarksUseCase: BookMarksUseCase
) :
    ViewModel() {


    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState>
        get() = _state

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        bookMarksUseCase.getAllBookmarksUseCase().onEach {

                items ->
            _state.update {

                it.copy(
                    bookmarks = items
                )
            }
        }.launchIn(viewModelScope)
    }
}