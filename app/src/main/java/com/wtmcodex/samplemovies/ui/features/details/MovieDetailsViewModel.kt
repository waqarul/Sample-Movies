package com.wtmcodex.samplemovies.ui.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.domain.interactor.GetMovieDetailsUseCase
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val TAG = this::class.simpleName

    private val viewModelState = MutableStateFlow(MovieDetailState.Empty)

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MovieDetailState.Empty
        )

    fun createInitialState(): MovieDetailState = MovieDetailState.Empty

    fun getMovieDetails(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            movieDetailsUseCase(
                GetMovieDetailsUseCase.Param(movieId = id)
            ).handle(
                onSuccess = { onMovieFetchSuccess(it) }
            )
        }
    }

    private fun onMovieFetchSuccess(result: Result<Movie?>) {
        when (result) {
            is Result.Success -> {
                viewModelState.update {
                    it.copy(
                        screenState = ScreenState.Success,
                        movie = result.data,
                        error = null
                    )
                }
            }
            is Result.Error -> {
                viewModelState.update {
                    it.copy(
                        screenState = ScreenState.Error,
                        movie = null,
                        error = result.exception.localizedMessage
                    )
                }
            }
        }
    }
}