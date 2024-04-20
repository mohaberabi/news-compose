package com.example.newsapp.features.news.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.core.util.Dimens


@Composable

fun NewsBottomNavBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavData> = emptyList(),
    selected: Int = 0,
    onSelect: (Int) -> Unit,

    ) {


    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {

        items.forEachIndexed { index, item ->
            val isSelected = index == selected
            val selectedColor = MaterialTheme.colorScheme.primary
            NavigationBarItem(

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    selectedTextColor = selectedColor,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                ),
                selected = isSelected,
                onClick = { onSelect(index) },
                icon = {


                    Column {
                        Icon(imageVector = item.icon, contentDescription = item.label)
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                        )
                        Spacer(modifier = Modifier.height(Dimens.xs))
                    }
                }
            )
        }
    }

}


data class BottomNavData(val icon: ImageVector, val label: String)