package com.nao20010128nao.FuckinCalculator

import android.app.Application
import com.beardedhen.androidbootstrap.TypefaceProvider

class TheApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TypefaceProvider.registerDefaultIconSets()
    }
}
