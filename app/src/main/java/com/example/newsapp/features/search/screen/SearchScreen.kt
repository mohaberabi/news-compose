package com.example.newsapp.features.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.core.composables.ArticleBuilder
import com.example.newsapp.core.composables.SearchField
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.search.viewmodel.SearchEvent
import com.example.newsapp.features.search.viewmodel.SearchState

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit = {},
    onGoToDetails: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(Dimens.md)
            .statusBarsPadding()
    ) {

        SearchField(
            onSearchPress = {
                event(SearchEvent.SearchRequested)

            },
            onChanged = {
                event(SearchEvent.SearchQueryChanged(it))

            },
            value = state.query,

            readOnly = false
        )
        Spacer(modifier = Modifier.height(Dimens.md))
        state.news?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleBuilder(articles = articles, onClick = { art -> onGoToDetails(art) })
        }
    }
}