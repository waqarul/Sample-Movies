package com.wtmcodex.samplemovies.data.movie.remote

import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.network.MovieDTO
import com.wtmcodex.samplemovies.model.network.TMDBMovieDTO

interface MovieRemoteDataSource {
    suspend fun getMovies(page: Int): Result<TMDBMovieDTO?>
    suspend fun getMovieDetails(id: Long): Result<MovieDTO?>
}