package com.deltax72.onlineshop.screens.catalog.states

data class CollapsableSection(
    val title: String,
    val rows: Map<String, () -> Unit>,
)