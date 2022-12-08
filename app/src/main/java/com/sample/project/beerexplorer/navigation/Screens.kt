package com.sample.project.beerexplorer.navigation

import androidx.navigation.NavHostController
import com.sample.project.beerguide.presentation.navigation.BeerDetailsDestination
import com.sample.project.beerguide.presentation.navigation.BeerListDestination

class Screens(navController: NavHostController) {
    val beerList = {
        navController.navigate(BeerListDestination.route)
    }
    val beerDetails: (Long) -> Unit = { beerId ->
        navController.navigate("details/$beerId") {
            popUpTo(BeerDetailsDestination.route)
        }
    }
}
