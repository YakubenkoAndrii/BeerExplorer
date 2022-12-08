package com.sample.project.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.project.core.data.state.Result
import com.sample.project.core.data.state.asResult
import com.sample.project.core.domain.preferences.Preferences
import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.onboarding.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val preferences: Preferences
) : ViewModel() {

    val onboardingUiState: StateFlow<OnboardingUiState> = onboardingUiState(
        onboardingRepository = onboardingRepository
    )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = OnboardingUiState.Loading
        )

    private fun onboardingUiState(
        onboardingRepository: OnboardingRepository
    ): Flow<OnboardingUiState> {
        // Get the list of onboarding screens
        val screenList: Flow<List<OnboardingItem>> = onboardingRepository.getOnboarding()
        return screenList
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        OnboardingUiState.Success(
                            result.data
                        )
                    }
                    is Result.Loading -> {
                        OnboardingUiState.Loading
                    }
                    is Result.Error -> {
                        OnboardingUiState.Error(
                            result.exception?.message
                        )
                    }
                }
            }
    }

    fun saveShouldShowOnboarding(shouldShowOnboarding: Boolean) {
        preferences.saveShouldShowOnboarding(shouldShowOnboarding)
    }

    sealed class OnboardingUiState {
        data class Success(val onboardingItems: List<OnboardingItem>) : OnboardingUiState()
        data class Error(val error: String?) : OnboardingUiState()
        object Loading : OnboardingUiState()
    }
}
