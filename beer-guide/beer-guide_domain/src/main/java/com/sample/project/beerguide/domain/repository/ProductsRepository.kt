package com.sample.project.beerguide.domain.repository

import com.sample.project.beerguide.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    /**
     * Returns list of beers.
     */
    fun getBeers(): Flow<List<Beer>>

    /**
     * Returns beer details by Id.
     *
     * @param beerId - the field used to find particular beer.
     */
    suspend fun getBeerDetailsById(beerId: Long): Beer?
}
