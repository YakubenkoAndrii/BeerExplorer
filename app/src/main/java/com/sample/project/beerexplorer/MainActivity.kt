package com.sample.project.beerexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel
import com.sample.project.beerexplorer.navigation.Navigation
import com.sample.project.beerexplorer.ui.splash.SplashViewModel
import com.sample.project.core_ui.theme.BeerExplorerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private val sharedBeerViewModel: SharedBeerViewModel by viewModels()

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading
        }

        setContent {
            BeerExplorerAppTheme {
                navController = rememberAnimatedNavController()
                Navigation(
                    navController = navController,
                    startDestination = splashViewModel.startDestination,
                    activity = this,
                    sharedBeerViewModel = sharedBeerViewModel
                )
            }
        }
    }
}
