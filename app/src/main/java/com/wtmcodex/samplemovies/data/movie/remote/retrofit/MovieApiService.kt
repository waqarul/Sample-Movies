package com.wtmcodex.samplemovies.data.movie.remote.retrofit

import com.wtmcodex.samplemovies.constants.APIConstants
import com.wtmcodex.samplemovies.model.network.MovieDTO
import com.wtmcodex.samplemovies.model.network.TMDBMovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET(APIConstants.GET_POPULAR_MOVIES)
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<TMDBMovieDTO>

    @GET(APIConstants.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String
    ): Response<MovieDTO>
}