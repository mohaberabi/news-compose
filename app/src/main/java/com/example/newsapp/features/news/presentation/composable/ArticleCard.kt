package com.example.newsapp.features.news.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.core.theme.NewsAppTheme
import com.example.newsapp.core.util.Dimens
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.domain.model.ArticleSource


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit = {},

    ) {


    val context = LocalContext.current


    Row(
        modifier = modifier.clickable {
            onClick()
        }
    ) {


        AsyncImage(
            modifier = Modifier
                .size(Dimens.articleImageSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.xs)
                .height(Dimens.articleImageSize)
        ) {

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = colorResource(id = R.color.textDisplay)),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Row {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.bodySmall
                        .copy(
                            color = colorResource(id = R.color.textDisplay),
                            fontWeight = FontWeight.Bold
                        ),
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(Dimens.xs))
                Icon(painter = painterResource(id = R.drawable.ic_time), contentDescription = null)
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.bodySmall
                        .copy(
                            color = colorResource(id = R.color.textDisplay),
                            fontWeight = FontWeight.Bold
                        ),
                    overflow = TextOverflow.Ellipsis,
                )
            }


        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)

@Composable

fun PreviewArticleCard() {
    val temp = Article(
        "Mohab Erabi",
        "Mohab erabi has produced another dummy app to tell us how loser  he is ",
        " i dont know why this gut is just not looking for another thing to do he is a big loser ",
        "17-4-2024",
        ArticleSource("23", "sadsad"),
        "Mohab Erabi The Same Loser Gut ",
        "https:://mohbaerabi.loser.com",
        ""
    )
    NewsAppTheme {
        ArticleCard(article = temp)


    }

}