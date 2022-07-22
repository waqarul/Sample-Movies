package com.wtmcodex.samplemovies.data.movie.local.dao

import androidx.room.*
import com.wtmcodex.samplemovies.constants.DatabaseConstants
import com.wtmcodex.samplemovies.data.movie.local.entity.MovieEntity

@Dao
interface MovieDao : BaseDao<MovieEntity> {
    @Insert
    suspend fun insertMovie(repository: MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEntity)

    @Query(DatabaseConstants.QUERY_SELECT_MOVIES)
    suspend fun getAllMovies(): List<MovieEntity>?

    @Query(DatabaseConstants.QUERY_SELECT_MOVIES_BY_ID)
    suspend fun getMovieByID(id: Long): MovieEntity?

    @Query(DatabaseConstants.QUERY_DELETE_MOVIES)
    suspend fun deleteAllMovies()
}