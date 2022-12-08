package com.sample.project.beerguide.data.model.network

import com.google.gson.annotations.SerializedName

data class BeerNetwork(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("abv") val abv: String,
    @SerializedName("food_pairing") val foodPairing: List<String>
)
