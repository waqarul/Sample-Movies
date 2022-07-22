package com.wtmcodex.samplemovies.data.movie.repository

import com.wtmcodex.samplemovies.data.movie.local.MovieLocalDataSource
import com.wtmcodex.samplemovies.data.movie.mapper.toDomain
import com.wtmcodex.samplemovies.data.movie.remote.MovieRemoteDataSource
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.model.RemoteException
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.model.movie.TMDBMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getMovies(page: Int): Result<TMDBMovie?> =
        withContext(ioDispatcher) {
            try {
                return@withContext when (val response =
                    remoteDataSource.getMovies(page)) {
                    is Result.Success -> {
                        if (response.data != null) {
                            val result = response.data.toDomain()
                            Result.Success(result)
                        } else {
                            Result.Success(null)
                        }
                    }
                    is Result.Error -> {
                        Result.Error(response.exception)
                    }
                    else -> Result.Success(null)
                }
            } catch (ex: RemoteException) {
                Result.Error(ex)
            }
        }

    override suspend fun getMovieDetails(id: Long): Result<Movie?> =
        withContext(ioDispatcher) {
            try {
                return@withContext when (val response =
                    remoteDataSource.getMovieDetails(id)) {
                    is Result.Success -> {
                        if (response.data != null) {
                            val result = response.data.toDomain()
                            Result.Success(result)
                        } else {
                            Result.Success(null)
                        }
                    }
                    is Result.Error -> {
                        Result.Error(response.exception)
                    }
                    else -> Result.Success(null)
                }
            } catch (ex: RemoteException) {
                Result.Error(ex)
            }
        }
}
