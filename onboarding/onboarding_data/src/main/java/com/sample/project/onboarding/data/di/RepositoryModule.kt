package com.sample.project.onboarding.data.di

import com.sample.project.onboarding.data.repository.OnboardingRepositoryImpl
import com.sample.project.onboarding.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindOnboardingRepository(
        onboardingRepository: OnboardingRepositoryImpl
    ): OnboardingRepository
}
