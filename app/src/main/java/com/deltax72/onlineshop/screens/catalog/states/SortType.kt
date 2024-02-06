package com.deltax72.onlineshop.screens.catalog.states

sealed class SortType(val type: String) {
    object None: SortType("none")

    object ByPopularity: SortType("По популярности")

    object ByPriceReduction: SortType("По убыванию цены")

    object ByPriceIncrease: SortType("По возрастанию цены")
}
