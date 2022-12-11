package com.sample.project.testing.data.repository

import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.beerguide.domain.repository.ProductsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class TestProductsRepository : ProductsRepository {

    /**
     * The backing hot flow for the list of beers for testing.
     */
    private val beerItemsFlow: MutableSharedFlow<List<Beer>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getBeers(): Flow<List<Beer>> = beerItemsFlow

    override suspend fun getBeerDetailsById(beerId: Long): Beer? {
        return beerItemsFlow.first().find { it.id == beerId }
    }

    /**
     * A test-only API to allow controlling the list of beers from tests.
     */
    fun sendBeerItems(beers: List<Beer>) {
        beerItemsFlow.tryEmit(beers)
    }
}
