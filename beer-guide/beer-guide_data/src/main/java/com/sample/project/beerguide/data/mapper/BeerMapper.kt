package com.sample.project.beerguide.data.mapper

import com.sample.project.beerguide.data.local.entity.BeerEntity
import com.sample.project.beerguide.data.model.network.BeerNetwork
import com.sample.project.beerguide.domain.model.Beer

/**
 * Mapper from network data class [BeerNetwork] representation to local class [Beer].
 */
fun BeerNetwork.toLocalBeer(): Beer =
    Beer(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        tagline = tagline,
        abv = abv.toDouble(),
        foodPairing = foodPairing
    )

/**
 * Mapper from entity [BeerEntity] representation to local class [Beer].
 */
fun BeerEntity.toLocalBeer(): Beer =
    Beer(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        tagline = tagline,
        abv = abv.toDouble(),
        foodPairing = foodPairing
    )

/**
 * Mapper from local data class [Beer] representation to [BeerEntity].
 */
fun Beer.toBeerEntity(): BeerEntity =
    BeerEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        tagline = tagline,
        abv = abv.toString(),
        foodPairing = foodPairing
    )
