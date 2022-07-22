package com.wtmcodex.samplemovies.data.movie.remote

import com.wtmcodex.samplemovies.data.movie.remote.retrofit.MovieApiService
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.di.scope.TMDBApiKey
import com.wtmcodex.samplemovies.model.RemoteException
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.network.MovieDTO
import com.wtmcodex.samplemovies.model.network.TMDBMovieDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    @TMDBApiKey private val apiKey: String,
    private val apiService: MovieApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    MovieRemoteDataSource {
    override suspend fun getMovies(page: Int): Result<TMDBMovieDTO?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getMovies(apiKey, page)
                if (result.isSuccessful) {
                    val movies = result.body()
                    Result.Success(movies)
                } else {
                    Result.Success(null)
                }
            } catch (exception: RemoteException) {
                Result.Error(exception)
            }
        }

    override suspend fun getMovieDetails(id: Long): Result<MovieDTO?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getMovieDetails(id, apiKey)
                if (result.isSuccessful) {
                    val movies = result.body()
                    Result.Success(movies)
                } else {
                    Result.Success(null)
                }
            } catch (exception: RemoteException) {
                Result.Error(exception)
            }
        }
}