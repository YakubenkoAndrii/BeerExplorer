package com.sample.project.beerguide.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.project.beerguide.data.local.converter.FoodPairingConverter
import com.sample.project.beerguide.data.local.entity.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    FoodPairingConverter::class
)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
}
