package com.wtmcodex.samplemovies.di

import com.wtmcodex.samplemovies.data.movie.repository.MovieRepository
import com.wtmcodex.samplemovies.data.movie.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}