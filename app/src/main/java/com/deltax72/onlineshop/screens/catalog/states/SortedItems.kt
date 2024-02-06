package com.deltax72.onlineshop.screens.catalog.states

import com.deltax72.onlineshop.data.entities.Items

data class SortedItems(
    val items: Items = Items(),
    val sortType: SortType = SortType.None,
    val tag: Tag = Tag.All()
)
