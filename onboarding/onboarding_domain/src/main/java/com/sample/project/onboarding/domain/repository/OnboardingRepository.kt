package com.sample.project.onboarding.domain.repository

import com.sample.project.onboarding.domain.model.OnboardingItem
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    /**
     * Returns list of onboarding screens
     */
    fun getOnboarding(): Flow<List<OnboardingItem>>
}
