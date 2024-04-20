package com.example.newsapp.features.news.data.remote.api

import com.example.newsapp.features.news.data.remote.api.ApiConst.apiKey
import com.example.newsapp.features.news.data.remote.api.ApiConst.everythingEndPoint
import com.example.newsapp.features.news.data.remote.dto.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query


object ApiConst {
    const val baseUrl = "https://newsapi.org/v2/"
    const val sources = "bbc-news,abc-news,al-jazeera-english"

    const val apiKey = "696ccfdb52634098864b84332e2070f4"

    const val everythingEndPoint = "everything"
}

interface NewsApi {

    @GET(everythingEndPoint)
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String = ApiConst.sources,
        @Query("apiKey") apiKey: String = ApiConst.apiKey,
    ): ArticleResponse

    @GET(everythingEndPoint)
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sources") sources: String = ApiConst.sources,
        @Query("apiKey") apiKey: String = ApiConst.apiKey,
    ): ArticleResponse
}