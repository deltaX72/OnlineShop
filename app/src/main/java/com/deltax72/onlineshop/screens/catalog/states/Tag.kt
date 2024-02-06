package com.deltax72.onlineshop.screens.catalog.states

sealed class Tag(
    val name: String,
    val key: String,
    open val isSelected: Boolean
) {
    data class All(
        override val isSelected: Boolean = false
    ): Tag("Смотреть все", "all", isSelected)

    data class Face(
        override val isSelected: Boolean = false
    ): Tag("Лицо", "face", isSelected)

    data class Body(
        override val isSelected: Boolean = false
    ): Tag("Тело", "body", isSelected)

    data class Suntan(
        override val isSelected: Boolean = false
    ): Tag("Загар", "suntan", isSelected)

    data class Mask(
        override val isSelected: Boolean = false
    ): Tag("Маски", "mask", isSelected)

    companion object {
        fun Tag.copy(
            isSelected: Boolean = this.isSelected
        ) = when (this) {
            is All -> All(isSelected)
            is Face -> Face(isSelected)
            is Body -> Body(isSelected)
            is Suntan -> Suntan(isSelected)
            is Mask -> Mask(isSelected)
        }
    }
}
