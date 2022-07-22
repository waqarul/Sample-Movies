package com.wtmcodex.samplemovies.di

import android.content.Context
import com.wtmcodex.samplemovies.R
import com.wtmcodex.samplemovies.di.scope.TMDBApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @TMDBApiKey
    fun provideTMDBApiKey(@ApplicationContext context: Context): String =
        context.getString(R.string.tmdb_api_key)
}
