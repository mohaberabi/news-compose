package com.example.newsapp.features.news.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.core.composables.ArticleBuilder
import com.example.newsapp.core.composables.SearchField
import com.example.newsapp.core.composables.articlesPaging
import com.example.newsapp.core.navigation.Route
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.news.domain.model.Article


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    articles: articlesPaging,
    onGoToSearch: () -> Unit,
    onGoToDetails: (Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(0, endInclusive = 9)).joinToString(
                    separator = "\uD83d\uDFE5"
                ) { it.title }
            } else {
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.sm)
            .statusBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,

            modifier = Modifier.size(100.dp)
        )


        Spacer(
            modifier = Modifier.height(Dimens.md)
        )
        SearchField(
            modifier = Modifier.fillMaxWidth(),
            onSearchPress = {},
            readOnly = true,
            onClick = {
                onGoToSearch()
            }
        )

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.md)
                .basicMarquee(),
            fontSize = 12.sp,
            color = Color.Gray
        )
        ArticleBuilder(articles = articles,
            onClick = {
                onGoToDetails(it)
            })
    }
}