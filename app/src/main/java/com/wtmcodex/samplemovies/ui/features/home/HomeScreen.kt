package com.wtmcodex.samplemovies.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.wtmcodex.samplemovies.R
import com.wtmcodex.samplemovies.constants.APIConstants
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.ui.common.AsyncImage
import com.wtmcodex.samplemovies.ui.common.HomeAppBar
import com.wtmcodex.samplemovies.ui.common.LoadingItem
import com.wtmcodex.samplemovies.ui.common.LoadingView
import com.wtmcodex.samplemovies.ui.theme.AppColor
import com.wtmcodex.samplemovies.ui.theme.Black
import com.wtmcodex.samplemovies.ui.theme.Gray300

@Composable
fun HomeScreen(
    openSearch: () -> Unit,
    openMovieDetails: (Long) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(topBar = {
        HomeAppBar(
            title = stringResource(id = R.string.home_app_bar_title),
            searchClick = { },
            filterClick = { }
        )
    },
        scaffoldState = scaffoldState,
        content = {
            MovieListing(
                openMovieDetails = openMovieDetails,
                homeViewModel = homeViewModel
            )
        }
    )
}

@Composable
fun MovieListing(openMovieDetails: (Long) -> Unit, homeViewModel: HomeViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    when (state.screenState) {
        is ScreenState.Loading -> {
        }
        is ScreenState.Error -> {
            ErrorItem { homeViewModel.initData() }
        }
        is ScreenState.Success -> {
            val lazyMovieItems = state.movies?.collectAsLazyPagingItems()
            lazyMovieItems?.let { movieItems ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                    content = {
                        items(movieItems.itemCount) { index ->
                            movieItems[index]?.let {
                                MovieItem(movie = it, onClick = openMovieDetails)
                            }
                        }

                        movieItems.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    item { LoadingView(modifier = Modifier.fillMaxSize()) }
                                }
                                loadState.append is LoadState.Loading -> {
                                    item { LoadingItem() }
                                    item { LoadingItem() }
                                }
                            }
                        }
                    })
            }
        }
    }
}

@Composable
fun ErrorItem(buttonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.home_screen_error_message),
            textAlign = TextAlign.Center,
            color = Black,
            style = typography.headlineMedium
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = buttonClick
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_retry),
                style = typography.labelLarge
            )
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .background(Color.Transparent)
            .clickable(onClick = { onClick(movie.id) }),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Gray300,
        elevation = 12.dp
    ) {
        Box {
            AsyncImage(
                url = "${APIConstants.IMAGE_URL}${movie.posterPath}",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(vertical = 2.dp)

            ) {
                TextItem(
                    text = movie.title,
                    modifier = Modifier.padding(bottom = 4.dp),
                    textStyle = typography.bodyMedium
                )
                TextItem(
                    text = "${movie.voteCount} votes",
                    modifier = Modifier.padding(bottom = 4.dp),
                    textStyle = typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun TextItem(text: String, modifier: Modifier, textStyle: TextStyle) {
    androidx.compose.material3.Text(
        text = text,
        color = Color.White,
        maxLines = 1,
        style = textStyle,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .background(color = AppColor)
            .padding(all = 6.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem {
        //do nothing
    }
}