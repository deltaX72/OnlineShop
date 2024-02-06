package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "feedback")
data class Feedback(
    @ColumnInfo("count")
    @Json(name = "count")
    val count: String = "",

    @ColumnInfo("rating")
    @Json(name = "rating")
    val rating: Double = 0.0

)
