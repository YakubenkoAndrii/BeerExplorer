package com.sample.project.beerguide.presentation.viewmodel

import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.testing.data.repository.TestProductsRepository
import com.sample.project.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class SharedBeerViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SharedBeerViewModel
    private lateinit var repository: TestProductsRepository

    @Before
    fun setUp() {
        repository = TestProductsRepository()
        viewModel = SharedBeerViewModel(
            productsRepository = repository
        )
    }

    @Test
    fun uiStateBeerGuide_whenInitialized_thenShowLoading() = runTest {
        Assert.assertEquals(
            SharedBeerViewModel.BeerGuideUiState.Loading,
            viewModel.beerGuideUiState.value
        )
    }

    @Test
    fun uiStateBeerDetails_whenInitialized_thenShowLoading() = runTest {
        Assert.assertEquals(
            SharedBeerViewModel.BeerDetailsUiState.Loading,
            viewModel.beerDetailsUiState.value
        )
    }

    @Test
    fun uiStateBeerGuide_whenSuccess() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.beerGuideUiState.collect() }
        // To make sure BeerGuideUiState is success
        repository.sendBeerItems(testInputBeers)
        val beerGuideUiState = viewModel.beerGuideUiState.value
        assertIs<SharedBeerViewModel.BeerGuideUiState.Success>(beerGuideUiState)
        collectJob.cancel()
    }

    @Test
    fun uiStateBeerDetails_whenSuccess() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.beerDetailsUiState.collect() }
        // To make sure BeerDetailsUiState is success
        repository.sendBeerItems(testInputBeers)
        viewModel.getBeerDetailsById(0)
        val beerDetailsUiState = viewModel.beerDetailsUiState.value
        assertIs<SharedBeerViewModel.BeerDetailsUiState.Success>(beerDetailsUiState)
        collectJob.cancel()
    }
}

private const val BEER_1_NAME = "Obolon"
private const val BEER_2_NAME = "Corona"
private const val BEER_1_DESCRIPTION = "light"
private const val BEER_2_DESCRIPTION = "dark"
private const val BEER_TAGLINE = "tagline"
private const val ABV_STRONG = 5.1
private const val ABV_NON_STRONG = 4.0
private const val SCREEN_IMAGE_URL = ""
private const val FOOD_PAIRING_1 = "food first"
private const val FOOD_PAIRING_2 = "food second"

private val testInputBeers = listOf(
    Beer(
        id = 0,
        name = BEER_1_NAME,
        description = BEER_1_DESCRIPTION,
        imageUrl = SCREEN_IMAGE_URL,
        tagline = BEER_TAGLINE,
        abv = ABV_NON_STRONG,
        foodPairing = listOf(FOOD_PAIRING_1, FOOD_PAIRING_2)
    ),
    Beer(
        id = 1,
        name = BEER_2_NAME,
        description = BEER_2_DESCRIPTION,
        imageUrl = SCREEN_IMAGE_URL,
        tagline = BEER_TAGLINE,
        abv = ABV_STRONG,
        foodPairing = listOf(FOOD_PAIRING_1, FOOD_PAIRING_2)
    )
)
