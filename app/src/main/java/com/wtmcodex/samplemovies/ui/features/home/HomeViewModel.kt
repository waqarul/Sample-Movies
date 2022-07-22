package com.wtmcodex.samplemovies.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.domain.interactor.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: GetMovieUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val TAG = this::class.simpleName

    fun createInitialState(): HomeState = HomeState.Empty
    private val viewModelState = MutableStateFlow(HomeState.Empty)

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HomeState.Empty
        )

    init {
        initData()
    }

    fun initData() {
        val flow = movieUseCase.invoke(Unit)
        viewModelState.update { it.copy(screenState = ScreenState.Success, movies = flow) }
    }
}