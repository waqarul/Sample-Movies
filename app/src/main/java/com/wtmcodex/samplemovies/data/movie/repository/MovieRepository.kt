package com.wtmcodex.samplemovies.data.movie.repository

import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.model.movie.TMDBMovie

interface MovieRepository {
    suspend fun getMovies(page: Int): Result<TMDBMovie?>
    suspend fun getMovieDetails(id: Long): Result<Movie?>
}