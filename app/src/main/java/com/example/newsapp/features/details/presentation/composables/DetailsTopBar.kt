package com.example.newsapp.features.details.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.core.theme.NewsAppTheme
import com.example.newsapp.core.util.UnitFunc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBack: UnitFunc = {},
    onBrowseClick: UnitFunc = {},
    onBookMarkClick: UnitFunc = {},
    onShareClick: UnitFunc = {},
) {

    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        ),
        navigationIcon = {
            IconButton(
                onClick = onBack,
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }, actions = {
            IconButton(
                onClick = onShareClick,
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
            }
            IconButton(
                onClick = onBookMarkClick,
            ) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Bookmark")
            }
            IconButton(
                onClick = onBrowseClick,
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Brows")
            }

        }
    )
}

@Preview(showBackground = true )
@Composable

fun PreviewIt() {


    NewsAppTheme {
        DetailsTopBar()


    }
}
