package com.fadybassem.di

import android.content.Context
import androidx.room.Room
import com.fadybassem.core.ResourceProvider
import com.fadybassem.data.local.entities.MovieDao
import com.fadybassem.data.local.entities.MoviesDatabase
import com.fadybassem.util.DATABASE_NAME
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MoviesDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MoviesDatabase): MovieDao = db.movieDao()

}