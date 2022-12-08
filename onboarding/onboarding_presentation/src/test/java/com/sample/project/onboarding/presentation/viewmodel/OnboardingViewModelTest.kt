package com.sample.project.onboarding.presentation.viewmodel

import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.testing.data.preferences.TestPreferences
import com.sample.project.testing.data.repository.TestOnboardingRepository
import com.sample.project.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.collect
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class OnboardingViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: OnboardingViewModel
    private lateinit var repository: TestOnboardingRepository
    private lateinit var preferences: TestPreferences

    @Before
    fun setUp() {
        repository = TestOnboardingRepository()
        preferences = TestPreferences()
        viewModel = OnboardingViewModel(
            onboardingRepository = repository,
            preferences = preferences
        )
    }

    @Test
    fun uiStateOnboarding_whenInitialized_thenShowLoading() = runTest {
        assertEquals(
            OnboardingViewModel.OnboardingUiState.Loading,
            viewModel.onboardingUiState.value
        )
    }

    @Test
    fun uiStateOnboarding_whenScreensSuccess() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.onboardingUiState.collect() }
        // To make sure AuthorUiState is success
        repository.sendOnboardingItems(testInputScreens)
        val onboardingUiState = viewModel.onboardingUiState.value
        assertIs<OnboardingViewModel.OnboardingUiState.Success>(onboardingUiState)
        collectJob.cancel()
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
