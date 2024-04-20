package com.example.newsapp.features.news.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.core.composables.shimmered
import com.example.newsapp.core.theme.NewsAppTheme
import com.example.newsapp.core.util.Dimens


@Composable
fun ArticleShimmer(
    modifier: Modifier = Modifier
) {


    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.articleImageSize)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .shimmered()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.xs)
                .height(Dimens.articleImageSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = Dimens.md)
                    .shimmered()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = Dimens.md)
                        .height(15.dp)
                        .shimmered()
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleShimmer() {
    NewsAppTheme {

        ArticleShimmer()
    }
}