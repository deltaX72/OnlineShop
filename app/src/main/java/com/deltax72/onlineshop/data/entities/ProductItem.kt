package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.deltax72.onlineshop.data.Converters
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "product_item")
@TypeConverters(Converters::class)
data class ProductItem(
    @PrimaryKey
    @Json(name = "id")
    val id: String = "",

    @ColumnInfo("title")
    @Json(name = "title")
    val title: String = "",

    @ColumnInfo("subtitle")
    @Json(name = "subtitle")
    val subtitle: String = "",

    @ColumnInfo("price")
    @Json(name = "price")
    val price: Price = Price(),

    @ColumnInfo("feedback")
    @Json(name = "feedback")
    val feedback: Feedback = Feedback(),

    @ColumnInfo("tags")
    @Json(name = "tags")
    val tags: List<String> = emptyList(),

    @ColumnInfo("available")
    @Json(name = "available")
    val available: Int = 0,

    @ColumnInfo("description")
    @Json(name = "description")
    val description: String = "",

    @ColumnInfo("info")
    @Json(name = "info")
    val info: List<Info> = emptyList(),

    @ColumnInfo("ingredients")
    @Json(name = "ingredients")
    val ingredients: String = ""
)