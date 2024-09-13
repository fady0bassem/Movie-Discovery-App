package com.fadybassem.di

import android.content.Context
import com.fadybassem.core.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResourcesProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context)
    }
}