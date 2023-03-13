package com.rezafarahani.climate.ui

import com.rezafarahani.climate.common.UiText

data class UIState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: UiText? = null
)