package com.wtmcodex.samplemovies.data.movie.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wtmcodex.samplemovies.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_MOVIES)
class MovieEntity(
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Long,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val voteAverage: Double
)