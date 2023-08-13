package com.singaludra.moviebootcamp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MyApplication? = null
    }
}