package com.example.newsapp.features.bookmark.presnetation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.core.composables.ArticleBuilder
import com.example.newsapp.core.navigation.Route
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.bookmark.presnetation.viewmodel.BookmarkState
import com.example.newsapp.features.news.domain.model.Article


@Composable
fun BookMarksScreen(
    state: BookmarkState,
    onGoToDetails: (Article) -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(Dimens.sm)
    ) {

        Text(text = "Bookmarks", style = MaterialTheme.typography.titleLarge)

        ArticleBuilder(
            articles = state.bookmarks,
            onClick = { art ->
                onGoToDetails(art)
            }
        )

    }
}