package com.wtmcodex.samplemovies.data.movie.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wtmcodex.samplemovies.data.movie.repository.MovieRepository
import com.wtmcodex.samplemovies.model.Result
import com.wtmcodex.samplemovies.model.movie.Movie
import javax.inject.Inject


class MovieSource @Inject constructor(private val repository: MovieRepository) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            when (val result = repository.getMovies(page)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = result.data?.movies ?: emptyList(),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = page.plus(1)
                    )
                }
                is Result.Error -> {
                    LoadResult.Error(result.exception)
                }
                Result.Loading -> TODO()
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}