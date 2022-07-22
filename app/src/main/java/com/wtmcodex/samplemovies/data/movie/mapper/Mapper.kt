package com.wtmcodex.samplemovies.data.movie.mapper

import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.model.movie.TMDBMovie
import com.wtmcodex.samplemovies.model.network.MovieDTO
import com.wtmcodex.samplemovies.model.network.TMDBMovieDTO

fun TMDBMovieDTO.toDomain(): TMDBMovie = TMDBMovie(
    movies = movies.map {
        Movie(
            posterPath = it.posterPath,
            adult = it.adult,
            overview = it.overview,
            releaseDate = it.releaseDate,
            genreIDS = it.genreIDS,
            id = it.id,
            originalTitle = it.originalTitle,
            title = it.title,
            popularity = it.popularity,
            voteCount = it.voteCount,
            video = it.video,
            voteAverage = it.voteAverage
        )
    }
).apply {
    this.page = totalPages
    this.totalResults = totalResults
    this.totalPages = totalPages
}

fun MovieDTO.toDomain(): Movie =
    Movie(
        posterPath = posterPath,
        adult = adult,
        overview = overview,
        releaseDate = releaseDate,
        genreIDS = genreIDS,
        id = id,
        originalTitle = originalTitle,
        title = title,
        popularity = popularity,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage
    )