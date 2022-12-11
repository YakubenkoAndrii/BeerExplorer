package com.sample.project.beerguide.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.project.beerguide.data.local.entity.BeerEntity

/**
 * DAO for [BeerEntity] access
 */
@Dao
interface ProductsDao {
    @Query(value = "SELECT * FROM beer")
    suspend fun getBeers(): List<BeerEntity>

    /**
     * Inserts [beers] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceBeers(beers: List<BeerEntity>): List<Long>

    @Query(value = "SELECT * FROM beer WHERE id = :beerId")
    suspend fun getBeerDetailsById(beerId: Long): BeerEntity?
}
