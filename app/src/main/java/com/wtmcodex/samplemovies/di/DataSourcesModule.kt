package com.wtmcodex.samplemovies.di

import com.wtmcodex.samplemovies.data.movie.local.MovieLocalDataSource
import com.wtmcodex.samplemovies.data.movie.local.MovieLocalDataSourceImpl
import com.wtmcodex.samplemovies.data.movie.remote.MovieRemoteDataSource
import com.wtmcodex.samplemovies.data.movie.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}