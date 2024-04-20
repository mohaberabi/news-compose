package com.example.newsapp.features.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.features.news.data.local.daos.NewsDao
import com.example.newsapp.features.news.data.local.entity.ArticleLocal
import com.example.newsapp.features.news.mappers.ArticleTypeConvertor


@Database(
    entities = [ArticleLocal::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ArticleTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao
}