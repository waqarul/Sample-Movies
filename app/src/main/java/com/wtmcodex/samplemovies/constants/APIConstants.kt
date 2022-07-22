package com.wtmcodex.samplemovies.constants

interface APIConstants {
    companion object {
        const val GET_POPULAR_MOVIES = "movie/popular"
        const val GET_MOVIE_DETAILS = "movie/{movie_id}"

        const val READ_TIME_OUT_DELAY = 2L
        const val CONNECT_TIME_OUT_DELAY = 2L
    }
}