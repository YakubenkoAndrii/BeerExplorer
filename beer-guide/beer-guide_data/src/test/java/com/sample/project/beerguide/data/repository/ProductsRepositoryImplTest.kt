package com.sample.project.beerguide.data.repository

import com.google.common.truth.Truth
import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.testing.data.repository.TestProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {

    private lateinit var repository: TestProductsRepository

    @Before
    fun setUp() {
        repository = TestProductsRepository()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_beers_not_empty() = runTest {
        // To make sure beers is not empty
        repository.sendBeerItems(testInputBeers)
        assert(repository.getBeers().first().isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun beer_abv_check() = runTest {
        repository.sendBeerItems(testInputBeers)
        val beerList = repository.getBeers().first()
        val notStrongBeer = beerList.find { it.abv < 5 }
        val strongBeer = beerList.find { it.abv > 5 }
        Truth.assertThat(
            notStrongBeer?.isBeerStrong() == false && strongBeer?.isBeerStrong() == true
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_beer_by_id_success() = runTest {
        repository.sendBeerItems(testInputBeers)
        val beer = repository.getBeerDetailsById(0)
        Truth.assertThat(
            beer != null && beer.name == BEER_1_NAME && beer.description == BEER_1_DESCRIPTION
        )
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
