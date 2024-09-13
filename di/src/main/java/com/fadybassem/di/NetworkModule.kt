package com.fadybassem.di

import android.content.Context
import com.fadybassem.core.HandleApiError
import com.fadybassem.core.ResourceProvider
import com.fadybassem.data.remote.network.Interceptor
import com.fadybassem.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideHandleApiError(resourceProvider: ResourceProvider): HandleApiError {
        return HandleApiError(resourceProvider = resourceProvider)
    }

    @Singleton
    @Provides
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager =
        NetworkManager(context = context)

}