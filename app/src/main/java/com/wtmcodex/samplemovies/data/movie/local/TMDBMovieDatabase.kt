package com.wtmcodex.samplemovies.data.movie.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtmcodex.samplemovies.data.movie.local.dao.MovieDao
import com.wtmcodex.samplemovies.data.movie.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class TMDBMovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}