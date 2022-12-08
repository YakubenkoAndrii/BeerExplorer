package com.sample.project.beerguide.data.network

import com.sample.project.beerguide.data.model.network.BeerNetwork
import retrofit2.http.GET

interface BeerService {
    @GET("beers")
    suspend fun getBeers(): List<BeerNetwork>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}
