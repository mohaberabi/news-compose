package com.example.newsapp.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.core.util.Dimens


@Composable
fun Indicator(
    modifier: Modifier = Modifier,
    size: Int,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary

) {

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        repeat(size) {

                index ->
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(color = if (index == selectedIndex) selectedColor else Color.Gray)
            )
        }
    }
}