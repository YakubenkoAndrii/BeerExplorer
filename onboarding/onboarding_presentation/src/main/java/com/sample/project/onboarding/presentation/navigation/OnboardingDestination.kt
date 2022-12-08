package com.sample.project.onboarding.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.sample.project.onboarding.presentation.OnboardingRoute

object OnboardingDestination {
    val route = "onboarding"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboarding(
    navigateToBeerBeerList: () -> Unit
) {
    composable(
        route = OnboardingDestination.route,
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
        OnboardingRoute(
            navigateToBeerBeerList = navigateToBeerBeerList
        )
    }
}
