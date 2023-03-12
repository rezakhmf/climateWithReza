package com.rezafarahani.climate.data.remote

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {

    fun logBodyInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}