package com.sample.project.beerguide.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.sample.project.beerguide.presentation.BeerDetailsRoute
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel

object BeerDetailsDestination {
    const val beerIdArg = "beerId"
    val route = "details/{$beerIdArg}"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.beerDetails(
    onNavigateUp: () -> Unit,
    sharedBeerViewModel: SharedBeerViewModel
) {
    composable(
        route = BeerDetailsDestination.route,
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
                    durationMillis = 300
                )
            )
        }
    ) { navBackStackEntry ->
        val beerId =
            navBackStackEntry.arguments?.getString(BeerDetailsDestination.beerIdArg)?.toLong() ?: 0
        BeerDetailsRoute(
            onNavigateUp = onNavigateUp,
            beerId = beerId,
            viewModel = sharedBeerViewModel
        )
    }
}
