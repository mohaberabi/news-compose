package com.example.newsapp.core.util

import android.content.Context


sealed class UiText {

    data class DynamicText(val text: String) : UiText()

    data object Empty : UiText()
    data class StringRes(val resId: Int) : UiText()


    fun asString(context: Context): String {

        return when (this) {
            is DynamicText -> text
            is StringRes -> context.getString(resId)
            else -> ""
        }
    }
}