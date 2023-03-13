package com.rezafarahani.climate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.rezafarahani.climate.navigation.AppNavigation
import com.rezafarahani.climate.ui.theme.ClimateWithRezaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        ClimateWithRezaTheme {
            AppNavigation()
        }
        }
    }
}