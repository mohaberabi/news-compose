package com.example.newsapp.features.news.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.features.news.data.remote.api.ApiConst
import com.example.newsapp.features.news.data.remote.api.NewsApi
import com.example.newsapp.features.news.data.remote.dto.ArticleRemote
import com.example.newsapp.features.news.domain.model.Article

class ArticlePagingSource(
    private val api: NewsApi,
) : PagingSource<Int, ArticleRemote>() {


    private var totalCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleRemote> {

        val page = params.key ?: 1
        return try {
            val response = api.getNews(page = page)
            totalCount += response.articles.size
            val articles = response.articles.distinctBy {
                it.title
            }
            LoadResult.Page(
                articles,
                nextKey = if (totalCount == response.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleRemote>): Int? {

        return state.anchorPosition?.let { pos ->
            val page = state.closestPageToPosition(pos)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}