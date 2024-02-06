package com.deltax72.onlineshop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

data class Colors(
    val White: Color = Color(0xFFFFFFFF),
    val LightGrey: Color = Color(0xFFF8F8F8),
    val Pink: Color = Color(0xFFD62F89),
    val LightPink: Color = Color(0xFFFF8AC9),
    val Orange: Color = Color(0xFFF9A249),
    val Grey: Color = Color(0xFFA0A1A3),
    val DarkGrey: Color = Color(0xFF3E3E3E),
    val DarkBlue: Color = Color(0xFF52606D),
    val Black: Color = Color(0xFF000000),

    val Red: Color = Color(0xFFFF0000)
)

val LocalColors = compositionLocalOf { Colors() }

val MaterialTheme.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current