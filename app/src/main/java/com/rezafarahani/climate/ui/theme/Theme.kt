package com.rezafarahani.climate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ClimateWithRezaTheme (content: @Composable() () -> Unit) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}