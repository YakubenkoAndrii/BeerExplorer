package com.sample.project.beerguide.domain.model

data class Beer(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val tagline: String,
    val abv: Double,
    val foodPairing: List<String>
) {
    fun isBeerStrong(): Boolean = abv > 5
}
