package com.example.newsapp.core.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.presentation.composable.ArticleCard
import com.example.newsapp.features.news.presentation.composable.ArticleShimmer

typealias articlesPaging = LazyPagingItems<Article>


@Composable
fun PagerArticleBuilder(
    modifier: Modifier = Modifier,
    articles: articlesPaging,
    onClick: (Article) -> Unit = {},
) {
    val result = handleArticlePaging(articles = articles)
    if (result) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.md),
            contentPadding = PaddingValues(all = Dimens.sm)
        ) {

            items(count = articles.itemCount) { index ->
                articles[index]?.let { art ->
                    ArticleCard(
                        article = art,
                        onClick = {
                            onClick(art)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun ListArticleBuilder(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.md),
        contentPadding = PaddingValues(all = Dimens.sm)
    ) {

        items(articles) { art ->
            ArticleCard(
                article = art,
                onClick = {
                    onClick(art)
                },
            )

        }
    }
}

@Composable
fun ArticleBuilder(
    modifier: Modifier = Modifier,
    articles: articlesPaging,
    onClick: (Article) -> Unit = {},
) {
    PagerArticleBuilder(articles = articles, onClick = onClick, modifier = modifier)
}

@Composable
fun ArticleBuilder(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit = {},
) {
    ListArticleBuilder(articles = articles, onClick = onClick, modifier = modifier)
}


@Composable
fun handleArticlePaging(
    articles: articlesPaging
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ArticlesLoader()
            false
        }

        error != null -> {
            ErrorCard(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ArticlesLoader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.md),
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(10) {
            ArticleShimmer(
                modifier = Modifier.padding(horizontal = Dimens.md)
            )
        }
    }
}