package com.example.newsapp.core.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.features.bookmark.data.repository.BookMarkRepositoryImpl
import com.example.newsapp.features.bookmark.domain.repository.BookMarkRepository
import com.example.newsapp.features.bookmark.domain.usecase.AddToBookMarkUseCase
import com.example.newsapp.features.bookmark.domain.usecase.BookMarksUseCase
import com.example.newsapp.features.bookmark.domain.usecase.GetAllBookmarksUseCase
import com.example.newsapp.features.bookmark.domain.usecase.RemoveFromBookMarkUseCase
import com.example.newsapp.features.bookmark.domain.usecase.GetArticleUseCase
import com.example.newsapp.features.news.data.local.NewsDatabase
import com.example.newsapp.features.news.data.local.daos.NewsDao
import com.example.newsapp.features.news.data.remote.api.ApiConst
import com.example.newsapp.features.news.data.remote.api.NewsApi
import com.example.newsapp.features.news.data.repository.ApiArticleRepository
import com.example.newsapp.features.news.domain.repository.NewsRepository
import com.example.newsapp.features.news.domain.usecase.GetNewsUseCase
import com.example.newsapp.features.news.domain.usecase.NewsUseCases
import com.example.newsapp.features.news.domain.usecase.SearchNewsUseCase
import com.example.newsapp.features.news.mappers.ArticleTypeConvertor
import com.example.newsapp.features.onboarding.data.DataStoreUserStorage
import com.example.newsapp.features.onboarding.domain.storage.UserStorage
import com.example.newsapp.features.onboarding.domain.usecase.GetOnBoardingUseCase
import com.example.newsapp.features.onboarding.domain.usecase.OnBoardingUseCases
import com.example.newsapp.features.onboarding.domain.usecase.SaveOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton

    fun provideUserStorage(
        app: Application
    ): UserStorage {

        return DataStoreUserStorage(app)

    }


    @Provides
    @Singleton
    fun provideOnboardingUseCases(userStorage: UserStorage): OnBoardingUseCases {
        return OnBoardingUseCases(
            getOnBoardingUseCases = GetOnBoardingUseCase(userStorage),
            saveOnboardingUseCase = SaveOnboardingUseCase(userStorage)
        )
    }

    @Provides
    @Singleton

    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(ApiConst.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi):
            NewsRepository =
        ApiArticleRepository(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNewsUseCases = GetNewsUseCase(newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app, NewsDatabase::class.java,
            "news.db",
        ).addTypeConverter(ArticleTypeConvertor()).build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao


    @Provides
    @Singleton
    fun provideBookMarkRepository(newsDao: NewsDao): BookMarkRepository =
        BookMarkRepositoryImpl(newsDao)

    @Provides
    @Singleton
    fun provideBookMarkUSeCases(bookMarkRepository: BookMarkRepository): BookMarksUseCase =
        BookMarksUseCase(
            AddToBookMarkUseCase(bookMarkRepository),
            RemoveFromBookMarkUseCase(bookMarkRepository),
            GetAllBookmarksUseCase(bookMarkRepository),
            GetArticleUseCase(bookMarkRepository)
        )


}