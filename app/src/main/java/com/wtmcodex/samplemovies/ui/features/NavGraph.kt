package com.wtmcodex.samplemovies.ui.features

import androidx.navigation.NavHostController
import com.wtmcodex.samplemovies.ui.features.Destinations.MovieDetails
import com.wtmcodex.samplemovies.ui.features.Destinations.Search

object Destinations {
    const val Home = "home"
    const val MovieDetails = "movieDetails"
    const val Search = "search"

    object MovieDetailsArgs {
        const val MovieId = "movieId"
    }
}

class Actions(navHostController: NavHostController) {
    val openSearch: () -> Unit = {
        navHostController.navigate(Search)
    }

    val openMovieDetails: (Long) -> Unit = { movieId ->
        navHostController.navigate("$MovieDetails/$movieId")
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}