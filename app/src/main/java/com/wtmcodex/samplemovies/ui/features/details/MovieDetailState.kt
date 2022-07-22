package com.wtmcodex.samplemovies.ui.features.details

import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.model.movie.Movie

data class MovieDetailState(
    val screenState: ScreenState = ScreenState.Loading,
    val movie: Movie? = null,
    val error: String? = null
) {
    companion object {
        val Empty = MovieDetailState()
    }
}
