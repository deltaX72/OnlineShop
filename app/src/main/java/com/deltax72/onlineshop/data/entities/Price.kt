package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "price")
data class Price(
    @ColumnInfo("price")
    @Json(name = "price")
    val price: String = "",

    @ColumnInfo("discount")
    @Json(name = "discount")
    val discount: Int = 0,

    @ColumnInfo("priceWithDiscount")
    @Json(name = "priceWithDiscount")
    val priceWithDiscount: String = "",

    @ColumnInfo("unit")
    @Json(name = "unit")
    val unit: String = ""

)
