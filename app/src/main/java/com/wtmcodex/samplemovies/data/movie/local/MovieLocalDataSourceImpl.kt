package com.wtmcodex.samplemovies.data.movie.local

import com.wtmcodex.samplemovies.data.movie.local.dao.MovieDao
import com.wtmcodex.samplemovies.data.movie.local.entity.MovieEntity
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieLocalDataSource {
    override suspend fun insertAll(items: List<MovieEntity>) = withContext(ioDispatcher) {
        return@withContext movieDao.insertAll(items)
    }

    override suspend fun insertOrUpdate(movie: MovieEntity) =
        withContext(ioDispatcher) {
            val item = movieDao.getMovieByID(movie.id)
            if (item == null) {
                movieDao.insertMovie(movie)
            } else {
                movieDao.updateMovie(movie)
            }
        }

    override suspend fun getAllMovies(): List<MovieEntity>? =
        withContext(ioDispatcher) {
            return@withContext movieDao.getAllMovies()
        }

    override suspend fun getMovieByID(id: Long): MovieEntity? =
        withContext(ioDispatcher) {
            return@withContext movieDao.getMovieByID(id)
        }

    override suspend fun deleteAllMovies() = withContext(ioDispatcher) {
        return@withContext movieDao.deleteAllMovies()
    }
}