package com.example.newsapp.core.util

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.newsapp.R

object AppIcons {

    @Composable
    fun TimeIcon() {
        Icon(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = "Time Icon"
        )
    }

}