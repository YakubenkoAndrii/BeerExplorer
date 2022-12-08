package com.sample.project.beerguide.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "beer"
)
class BeerEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val imageUrl: String,
    @ColumnInfo val tagline: String,
    @ColumnInfo val abv: String,
    @ColumnInfo val foodPairing: List<String>
)
