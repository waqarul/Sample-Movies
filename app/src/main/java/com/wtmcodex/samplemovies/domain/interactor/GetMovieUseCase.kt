package com.wtmcodex.samplemovies.domain.interactor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wtmcodex.samplemovies.data.movie.base.FlowUseCase
import com.wtmcodex.samplemovies.data.movie.source.MovieSource
import com.wtmcodex.samplemovies.di.scope.IoDispatcher
import com.wtmcodex.samplemovies.model.movie.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieSource: MovieSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    FlowUseCase<Unit, PagingData<Movie>>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = Int.MAX_VALUE)) {
            movieSource
        }.flow
    }
}