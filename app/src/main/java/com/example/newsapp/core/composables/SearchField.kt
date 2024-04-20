package com.example.newsapp.core.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.newsapp.core.util.Dimens


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onChanged: (String) -> Unit = {},
    onClick: (() -> Unit)? = null,
    onSearchPress: () -> Unit,
    readOnly: Boolean,
    value: String = "",
) {

    val interActionSource = remember {

        MutableInteractionSource()
    }

    val isClicked = interActionSource.collectIsPressedAsState().value
    LaunchedEffect(isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            interactionSource = interActionSource,
            modifier = Modifier
                .fillMaxWidth()
                .searchFieldBorder(),
            value = value,
            onValueChange = onChanged,
            readOnly = readOnly,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchPress()
            }),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(Dimens.md)
                )
            },
            placeholder = {
                Text(text = "Search..", color = Color.Gray)
            },
        )
    }
}

private fun Modifier.searchFieldBorder() = composed {

    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}