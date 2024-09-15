package com.fadybassem.di

import com.fadybassem.core.HandleApiError
import com.fadybassem.data.local.entities.MovieDao
import com.fadybassem.data.remote.api.ApiService
import com.fadybassem.data.repository.MovieRepositoryImpl
import com.fadybassem.data.repository.WatchlistRepositoryImpl
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.domain.repository.WatchlistRepository
import com.fadybassem.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieApi: ApiService,
        handleApiError: HandleApiError,
        movieDao: MovieDao,
        networkManager: NetworkManager,
    ): MovieRepository = MovieRepositoryImpl(
        movieApi = movieApi,
        movieDao = movieDao,
        handleApiError = handleApiError,
        networkManager = networkManager
    )

    @Singleton
    @Provides
    fun provideWatchlistRepository(
        movieApi: ApiService,
        handleApiError: HandleApiError,
        movieDao: MovieDao,
        networkManager: NetworkManager,
    ): WatchlistRepository = WatchlistRepositoryImpl(
        movieDao = movieDao,
    )
}