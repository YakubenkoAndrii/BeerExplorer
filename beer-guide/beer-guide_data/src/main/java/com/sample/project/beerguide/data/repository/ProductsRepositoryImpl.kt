package com.sample.project.beerguide.data.repository

import com.sample.project.beerguide.data.local.ProductsDao
import com.sample.project.beerguide.data.mapper.toBeerEntity
import com.sample.project.beerguide.data.mapper.toLocalBeer
import com.sample.project.beerguide.data.network.BeerService
import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.beerguide.domain.repository.ProductsRepository
import com.sample.project.core.data.network.model.Dispatcher
import com.sample.project.core.data.network.model.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val beerService: BeerService,
    private val productsDao: ProductsDao
) : ProductsRepository {
    override fun getBeers(): Flow<List<Beer>> = flow {
        val beersLocal = productsDao.getBeers()
        if (beersLocal.isNotEmpty()) {
            emit(beersLocal.map { beerItem -> beerItem.toLocalBeer() })
        } else {
            val beersRemote = beerService.getBeers().map { it.toLocalBeer() }
            emit(beersRemote)
            saveBeers(beersRemote)
        }
    }.flowOn(ioDispatcher)

    override suspend fun getBeerDetailsById(beerId: Long): Beer? {
        return productsDao.getBeerDetailsById(beerId)?.toLocalBeer()
    }

    private suspend fun saveBeers(beers: List<Beer>) {
        productsDao.insertOrReplaceBeers(beers.map { beerItem -> beerItem.toBeerEntity() })
    }
}
