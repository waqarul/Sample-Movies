package com.wtmcodex.samplemovies.model.network

import com.google.gson.annotations.SerializedName

data class TMDBMovieDTO(
    @SerializedName("results")
    var movies: List<MovieDTO> = emptyList()
) : BaseDTO()