package com.wtmcodex.samplemovies.data.movie.base

sealed class ScreenState {
    object Loading : ScreenState()
    object Success : ScreenState()
    object Error : ScreenState()
}
