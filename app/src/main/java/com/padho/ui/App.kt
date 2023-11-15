package com.padho.ui

import android.app.Application
import com.fdbanks.utils.SharedPref
import com.padho.utils.MProgressBar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(applicationContext)
    }
}