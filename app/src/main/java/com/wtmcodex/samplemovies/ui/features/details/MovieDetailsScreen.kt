package com.wtmcodex.samplemovies.ui.features.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.wtmcodex.samplemovies.R
import com.wtmcodex.samplemovies.constants.APIConstants
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.extensions.getFormattedReleaseDate
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.ui.common.AsyncImage
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
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .testTag("MovieDetailsParent")
    ) {
        AsyncImage(
            url = "${APIConstants.IMAGE_URL}${movie.posterPath}",
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = movie.title,
                style = typography.displayMedium
            )
            Text(
                text = stringResource(id = R.string.detail_screen_overview),
                style = typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = movie.overview,
                style = typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.detail_screen_release_date),
                style = typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = movie.getFormattedReleaseDate(),
                style = typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.detail_screen_vote_count),
                style = typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "${movie.voteCount}",
                style = typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.detail_screen_vote_average),
                style = typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "${movie.voteAverage}",
                style = typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.detail_screen_popularity),
                style = typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "${movie.popularity}",
                style = typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
}