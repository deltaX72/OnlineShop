package com.deltax72.onlineshop.data

import androidx.room.TypeConverter
import com.deltax72.onlineshop.data.entities.Feedback
import com.deltax72.onlineshop.data.entities.Info
import com.deltax72.onlineshop.data.entities.Price
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.di.networkModule
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi: Moshi = networkModule.moshi


    private val priceAdapter = moshi.adapter(Price::class.java)

    private val feedbackAdapter = moshi.adapter(Feedback::class.java)

    private val infoType = Types.newParameterizedType(List::class.java, Info::class.java)
    private val infoAdapter = moshi.adapter<List<Info>>(infoType)

    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val listAdapter = moshi.adapter<List<String>>(listType)

    private val itemsType = Types.newParameterizedType(List::class.java, Info::class.java)
    private val itemsAdapter = moshi.adapter<List<ProductItem>>(itemsType)

    private val itemAdapter = moshi.adapter(ProductItem::class.java)


    @TypeConverter
    fun stringToPrice(string: String): Price? {
        return priceAdapter.fromJson(string)
    }

    @TypeConverter
    fun priceToString(price: Price?): String {
        return priceAdapter.toJson(price)
    }


    @TypeConverter
    fun stringToFeedback(string: String): Feedback? {
        return feedbackAdapter.fromJson(string)
    }

    @TypeConverter
    fun feedbackToString(feedback: Feedback?): String {
        return feedbackAdapter.toJson(feedback)
    }


    @TypeConverter
    fun stringToInfoItem(string: String): List<Info> {
        return infoAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun infoItemToString(feedback: List<Info>): String {
        return infoAdapter.toJson(feedback)
    }


    @TypeConverter
    fun toList(string: String): List<String> {
        return listAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return listAdapter.toJson(list)
    }


    @TypeConverter
    fun stringToItems(string: String): List<ProductItem> {
        return itemsAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun itemsToString(items: List<ProductItem>): String {
        return itemsAdapter.toJson(items)
    }


    @TypeConverter
    fun stringToItem(string: String): ProductItem? {
        return itemAdapter.fromJson(string)
    }

    @TypeConverter
    fun itemToString(item: ProductItem?): String {
        return itemAdapter.toJson(item)
    }
}