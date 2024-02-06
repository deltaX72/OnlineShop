package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.deltax72.onlineshop.data.Converters
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity
@TypeConverters(Converters::class)
data class Items(
    @ColumnInfo("items")
    @Json(name = "items")
    val items: List<ProductItem> = emptyList()
)
