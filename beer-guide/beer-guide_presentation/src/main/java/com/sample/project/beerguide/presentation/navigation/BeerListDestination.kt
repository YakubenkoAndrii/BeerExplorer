package com.sample.project.beerguide.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.sample.project.beerguide.presentation.BeerListRoute
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel

object BeerListDestination {
    val route = "beerList"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.beerList(
    onNavigateUp: () -> Unit,
    sharedBeerViewModel: SharedBeerViewModel,
    navigateToBeerDetails: (beerId: Long) -> Unit
) {
    composable(
        route = BeerListDestination.route,
        enterTransition = { // push
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 350
                )
            )
        },
        popExitTransition = { // pop
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 400
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 350
                )
            )
        }
    ) {
        BeerListRoute(
            onNavigateUp = onNavigateUp,
            viewModel = sharedBeerViewModel,
            navigateToBeerDetails = navigateToBeerDetails
        )
    }
}
