package com.wtmcodex.samplemovies.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.wtmcodex.samplemovies.R
import com.wtmcodex.samplemovies.data.movie.base.ScreenState
import com.wtmcodex.samplemovies.model.movie.Movie
import com.wtmcodex.samplemovies.ui.common.HomeAppBar
import com.wtmcodex.samplemovies.ui.common.LoadingItem
import com.wtmcodex.samplemovies.ui.common.LoadingView
import com.wtmcodex.samplemovies.ui.theme.Black

@Composable
fun HomeScreen(
    openSearch: () -> Unit,
    openMovieDetails: (Long) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    // HandleSideEffect(homeViewModel.uiSideEffect(), scaffoldState)
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
            LoadingView(modifier = Modifier.fillMaxSize())
        }
        is ScreenState.Error -> {
            ErrorItem { homeViewModel.initData() }
        }
        is ScreenState.Success -> {
            val lazyMovieItems = state.movies?.collectAsLazyPagingItems()
            lazyMovieItems?.let { movieItems ->
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
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
                            loadState.refresh is LoadState.Error -> {
                            }
                            loadState.append is LoadState.Error -> {
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
            .padding(
                bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp
            )
            .fillMaxWidth()
            .clickable(onClick = { onClick(movie.id) }),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 18.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = androidx.compose.ui.graphics.Color.Black
                )
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = movie.overview,
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem {
        //do nothing
    }
}