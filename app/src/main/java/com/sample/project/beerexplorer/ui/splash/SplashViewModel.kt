package com.sample.project.beerexplorer.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.project.beerguide.presentation.navigation.BeerListDestination
import com.sample.project.core.domain.preferences.Preferences
import com.sample.project.onboarding.presentation.navigation.OnboardingDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(OnboardingDestination.route)
        private set

    init {
        viewModelScope.launch {
            delay(2000)
            val isShowOnboarding = preferences.loadShouldShowOnboarding()
            startDestination = if (isShowOnboarding) {
                OnboardingDestination.route
            } else {
                BeerListDestination.route
            }
            isLoading = false
        }
    }
}
