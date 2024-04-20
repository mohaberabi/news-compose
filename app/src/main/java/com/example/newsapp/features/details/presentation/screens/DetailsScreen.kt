package com.example.newsapp.features.details.presentation.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.core.navigation.Route
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.core.util.UnitFunc
import com.example.newsapp.features.details.presentation.composables.DetailsTopBar
import com.example.newsapp.features.details.presentation.viewmodel.ArticleDetailsEvent
import com.example.newsapp.features.news.domain.model.Article


@Composable
fun DetailsScreen(
    article: Article,
    event: (ArticleDetailsEvent) -> Unit,
    navigateUp: UnitFunc
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailsTopBar(
            onBookMarkClick = {
                event(ArticleDetailsEvent.UpsertDeleteArticle(article))
            },
            onBack = {
                navigateUp()
            },
            onBrowseClick = {
                openWebBrowser(article.url, context)
            }, onShareClick = {
                share(article.url)
            }
        )


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                all = Dimens.md,
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(Dimens.md))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }


}

fun openWebBrowser(url: String, context: Context) {
    Intent(Intent.ACTION_VIEW).also {
        it.data = Uri.parse(url)
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
}

fun share(data: String) {
    Intent(Intent.ACTION_SEND).also {
        it.putExtra(Intent.EXTRA_TEXT, data)
        it.type = "text/plain"
    }
}