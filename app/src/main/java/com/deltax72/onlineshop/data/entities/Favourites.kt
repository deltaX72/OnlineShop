package com.deltax72.onlineshop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.deltax72.onlineshop.data.Converters

@Entity(tableName = "favourites")
@TypeConverters(Converters::class)
data class Favourites(
    @PrimaryKey
    val id: String,

    @ColumnInfo("item")
    val item: ProductItem
)