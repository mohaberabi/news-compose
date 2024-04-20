package com.example.newsapp.features.news.mappers

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapp.features.news.data.local.entity.ArticleLocal
import com.example.newsapp.features.news.data.local.entity.LocalSource
import com.example.newsapp.features.news.domain.model.Article
import com.example.newsapp.features.news.domain.model.ArticleSource


@ProvidedTypeConverter
class ArticleTypeConvertor {

    @TypeConverter
    fun sourceToString(source: LocalSource): String {

        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun sourceFromString(source: String): LocalSource {

        return source.split(",").let {
            LocalSource(it[0], it[1])
        }
    }
}

fun LocalSource.toSource(): ArticleSource {
    return ArticleSource(id, name)
}

fun ArticleLocal.toArticle(): Article {
    return Article(
        author ?: "",
        content ?: "",
        description ?: "",
        publishedAt ?: "",
        source.toSource(),
        title,
        url,
        urlToImage
    )
}