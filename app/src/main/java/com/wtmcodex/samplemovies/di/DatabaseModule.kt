package com.wtmcodex.samplemovies.di

import android.content.Context
import androidx.room.Room
import com.wtmcodex.samplemovies.constants.DatabaseConstants
import com.wtmcodex.samplemovies.data.movie.local.TMDBMovieDatabase
import com.wtmcodex.samplemovies.data.movie.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TMDBMovieDatabase {
        return Room.databaseBuilder(
            context,
            TMDBMovieDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: TMDBMovieDatabase): MovieDao {
        return database.movieDao()
    }
}