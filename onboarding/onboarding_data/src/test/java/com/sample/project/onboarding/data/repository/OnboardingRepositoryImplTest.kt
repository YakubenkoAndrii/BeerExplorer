package com.sample.project.onboarding.data.repository

import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.testing.data.repository.TestOnboardingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OnboardingRepositoryImplTest {

    private lateinit var repository: TestOnboardingRepository

    @Before
    fun setUp() {
        repository = TestOnboardingRepository()
        val screensToInsert = mutableListOf<OnboardingItem>()
        screensToInsert.add(
            OnboardingItem(
                title = "",
                imageUrl = "",
                description = ""
            )
        )
        repository.insertOnboardingScreens(screensToInsert)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_onboarding_screens_not_empty() = runTest {
        // To make sure onboarding screens is not empty
        repository.sendOnboardingItems(testInputScreens)
        assert(repository.getOnboarding().first().isNotEmpty())
    }
}

private const val SCREEN_1_TITLE = "Hello"
private const val SCREEN_2_TITLE = "Beer Explorer"
private const val SCREEN_1_DESCRIPTION = "it's a simple application"
private const val SCREEN_2_DESCRIPTION = "about breeds of beer"
private const val SCREEN_IMAGE_URL = ""

private val testInputScreens = listOf(
    OnboardingItem(
        title = SCREEN_1_TITLE,
        description = SCREEN_1_DESCRIPTION,
        imageUrl = SCREEN_IMAGE_URL
    ),
    OnboardingItem(
        title = SCREEN_2_TITLE,
        description = SCREEN_2_DESCRIPTION,
        imageUrl = SCREEN_IMAGE_URL
    )
)
