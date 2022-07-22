package com.wtmcodex.samplemovies.data.movie.local

import com.wtmcodex.samplemovies.data.movie.local.entity.MovieEntity

interface MovieLocalDataSource {
    suspend fun insertAll(items: List<MovieEntity>)
    suspend fun insertOrUpdate(movie: MovieEntity)
    suspend fun getAllMovies(): List<MovieEntity>?
    suspend fun getMovieByID(id: Long): MovieEntity?
    suspend fun deleteAllMovies()
}