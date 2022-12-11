package com.sample.project.beerguide.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class FoodPairingConverter {
    @TypeConverter
    fun listToJsonString(value: List<String>?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<String>::class.java).toList()
}
