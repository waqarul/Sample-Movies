package com.wtmcodex.samplemovies.domain.interactor

import com.wtmcodex.samplemovies.data.movie.repository.MovieRepository
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.TMDBMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieInteractor {

    override suspend fun getMovies(page: Int): Result<TMDBMovie?> =
        withContext(ioDispatcher) {
            repository.getMovies(page)
        }
}
