package com.wtmcodex.samplemovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleMoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}