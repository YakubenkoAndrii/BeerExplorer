package com.sample.project.testing.data.repository

import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.onboarding.domain.repository.OnboardingRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestOnboardingRepository : OnboardingRepository {

    /**
     * The backing hot flow for the list of author ids for testing.
     */
    private val onboardingScreensFlow: MutableSharedFlow<List<OnboardingItem>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val onboardingScreens = mutableListOf<OnboardingItem>()

    override fun getOnboarding(): Flow<List<OnboardingItem>> = onboardingScreensFlow

    fun insertOnboardingScreens(onboardingItems: List<OnboardingItem>) {
        this.onboardingScreens.addAll(onboardingItems)
    }

    /**
     * A test-only API to allow controlling the list of authors from tests.
     */
    fun sendOnboardingItems(authors: List<OnboardingItem>) {
        onboardingScreensFlow.tryEmit(authors)
    }
}
