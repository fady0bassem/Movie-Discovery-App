package com.fadybassem.di

import com.fadybassem.data.remote.network.Interceptor
import com.fadybassem.data.remote.network_exception.HandleApiError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {

        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor)
            .connectTimeout(5, TimeUnit.MINUTES).callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES).retryOnConnectionFailure(true)
            .writeTimeout(5, TimeUnit.MINUTES).addInterceptor(httpLogging)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideHandleApiError(): HandleApiError {
        return HandleApiError()
    }
}