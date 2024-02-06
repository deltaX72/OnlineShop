package com.deltax72.onlineshop.screens.catalog.states

data class TagsList(
    val tags: List<Tag> = listOf(
        Tag.All(),
        Tag.Face(),
        Tag.Body(),
        Tag.Suntan(),
        Tag.Mask()
    )
)