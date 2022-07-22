package com.wtmcodex.samplemovies.ui.features.home

import androidx.paging.PagingData
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.model.movie.Movie
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val screenState: ScreenState = ScreenState.Loading,
    val movies: Flow<PagingData<Movie>>? = null,
    val error: String? = null
) {
    companion object {
        val Empty = HomeState()
    }
}
