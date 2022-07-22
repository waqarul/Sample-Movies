package com.wtmcodex.samplemovies.domain.interactor

import com.wtmcodex.samplemovies.data.movie.base.UseCase
import com.wtmcodex.samplemovies.data.movie.repository.MovieRepository
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.Movie
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    UseCase<GetMovieDetailsUseCase.Param, Result<Movie?>>(ioDispatcher) {

    override suspend fun execute(parameters: Param): Result<Movie?> {
        return repository.getMovieDetails(parameters.movieId)
    }

    data class Param(
        val movieId: Long
    )
}