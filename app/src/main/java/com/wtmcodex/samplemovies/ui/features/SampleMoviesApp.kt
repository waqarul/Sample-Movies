package com.wtmcodex.samplemovies.ui.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wtmcodex.samplemovies.ui.features.Destinations.Home
import com.wtmcodex.samplemovies.ui.features.Destinations.MovieDetails
import com.wtmcodex.samplemovies.ui.features.Destinations.MovieDetailsArgs.MovieId
import com.wtmcodex.samplemovies.ui.features.details.MovieDetailsScreen
import com.wtmcodex.samplemovies.ui.features.home.HomeScreen

@Composable
fun SampleMoviesApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(navController = navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(
                openSearch = actions.openSearch,
                openMovieDetails = actions.openMovieDetails
            )
        }
        composable(
            "$MovieDetails/{$MovieId}",
            arguments = listOf(
                navArgument(MovieId) { type = NavType.LongType }
            )
        ) {
            MovieDetailsScreen(
                movieId = it.arguments?.getLong(MovieId) ?: 0L,
                navigateBack = actions.navigateBack
            )
        }
    }
}