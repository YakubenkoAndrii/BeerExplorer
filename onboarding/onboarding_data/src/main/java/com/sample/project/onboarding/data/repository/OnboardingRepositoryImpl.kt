package com.sample.project.onboarding.data.repository

import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.onboarding.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor() : OnboardingRepository {
    override fun getOnboarding(): Flow<List<OnboardingItem>> = flowOf(
        listOf(
            OnboardingItem(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_4XB70NUcaqq" +
                        "0fvuEczWEmOB5LQ9YxUaT-Q&usqp=CAU",
                title = "Beer Explorer",
                description = "Hello, that is application for a beer drinkers."
            ),
            OnboardingItem(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_4XB70NUcaqq" +
                        "0fvuEczWEmOB5LQ9YxUaT-Q&usqp=CAU",
                title = "Find something new for you!",
                description = "You can find new breed of beer and also check all details what you" +
                        " want to know about drink."
            ),
            OnboardingItem(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_4XB70NUcaqq" +
                        "0fvuEczWEmOB5LQ9YxUaT-Q&usqp=CAU",
                title = "Hurry!",
                description = "Stop to spend your time, go ahead to see the beer )"
            )
        )
    )
}
