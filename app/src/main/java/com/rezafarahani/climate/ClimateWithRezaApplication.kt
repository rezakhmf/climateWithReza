package com.rezafarahani.climate

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClimateWithRezaApplication : ClimateWithRezaCoreApplicaiton() {

    override fun onCreate() {
        super.onCreate()
    }
}