package com.sample.project.beerexplorer.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel
import com.sample.project.beerguide.presentation.navigation.beerDetails
import com.sample.project.beerguide.presentation.navigation.beerList
import com.sample.project.onboarding.presentation.navigation.onboarding

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String,
    activity: ComponentActivity,
    sharedBeerViewModel: SharedBeerViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        onboarding(
            navigateToBeerBeerList = screen.beerList
        )
        beerList(
            onNavigateUp = { activity.finish() },
            sharedBeerViewModel = sharedBeerViewModel,
            navigateToBeerDetails = screen.beerDetails
        )
        beerDetails(
            onNavigateUp = { navController.popBackStack() },
            sharedBeerViewModel = sharedBeerViewModel
        )
    }
}
