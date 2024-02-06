package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "info_item")
data class Info(
    @ColumnInfo("title")
    @Json(name = "title")
    val title: String,

    @ColumnInfo("value")
    @Json(name = "value")
    val value: String
)
