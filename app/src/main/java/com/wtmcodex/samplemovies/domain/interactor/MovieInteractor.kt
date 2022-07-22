package com.wtmcodex.samplemovies.domain.interactor

import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.TMDBMovie

interface MovieInteractor {
    suspend fun getMovies(page: Int): Result<TMDBMovie?>
}