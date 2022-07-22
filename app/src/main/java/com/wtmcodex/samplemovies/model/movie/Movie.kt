package com.wtmcodex.samplemovies.model.movie

data class Movie(
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIDS: List<Long>? = emptyList(),
    val id: Long,
    val originalTitle: String,
    val title: String,
    val popularity: Double,
    val voteCount: Long,
    val video: Boolean,
    val voteAverage: Double
)