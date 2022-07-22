package com.wtmcodex.samplemovies.model.movie

import com.wtmcodex.samplemovies.model.BaseModel

data class TMDBMovie(
    val movies: List<Movie> = emptyList()
) : BaseModel()