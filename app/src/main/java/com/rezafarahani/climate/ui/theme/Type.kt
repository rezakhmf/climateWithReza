package com.rezafarahani.climate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rezafarahani.climate.R

val Monster = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.Thin,
        fontSize = 60.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Monster,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)