package com.deltax72.onlineshop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deltax72.onlineshop.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val sfProDisplayMedium = FontFamily(
    Font(R.font.sf_pro_display_medium),
)

val sfProDisplayRegular = FontFamily(
    Font(R.font.sf_pro_display_regular)
)

data class Styles(
    val largeTitle: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 20.sp,
    ),
    val title1: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 16.sp
    ),
    val title2: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 14.sp
    ),
    val title3: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 12.sp
    ),
    val title4: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 14.sp
    ),
    val text1: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 12.sp
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 10.sp
    ),
    val buttonText1: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 12.sp
    ),
    val buttonText2: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 14.sp
    ),
    val elementText: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 9.sp
    ),
    val priceText: TextStyle = TextStyle(
        fontFamily = sfProDisplayMedium,
        fontSize = 24.sp
    ),
    val placeholderText: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 16.sp
    ),
    val linkText: TextStyle = TextStyle(
        fontFamily = sfProDisplayRegular,
        fontSize = 10.sp
    )
)

val LocalStyles = compositionLocalOf { Styles() }

val MaterialTheme.styles: Styles
    @Composable
    @ReadOnlyComposable
    get() = LocalStyles.current
