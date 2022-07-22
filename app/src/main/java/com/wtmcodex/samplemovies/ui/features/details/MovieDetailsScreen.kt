package com.wtmcodex.samplemovies.ui.features.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.ui.common.LoadingView

@Composable
fun MovieDetailsScreen(
    movieId: Long,
    navigateBack: () -> Unit,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {

    movieDetailsViewModel.getMovieDetails(movieId)

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = movieDetailsViewModel.uiState
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = movieDetailsViewModel.createInitialState())

    when (state.screenState) {
        is ScreenState.Loading -> {
            LoadingView(modifier = Modifier.fillMaxSize())
        }
        is ScreenState.Error -> {
            navigateBack.invoke()
        }
        is ScreenState.Success -> {
            state.movie?.let {
                MovieDetails(movie = it)
            }
        }
    }
}

@Composable
fun MovieDetails(movie: Movie) {
    Text(
        text = movie.title,
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 18.sp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = androidx.compose.ui.graphics.Color.Black
    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
}