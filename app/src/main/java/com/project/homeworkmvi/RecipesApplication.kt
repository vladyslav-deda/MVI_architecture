package com.project.homeworkmvi

import android.app.Application
import com.codemonkeylabs.fpslibrary.TinyDancer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TinyDancer.create()
            .redFlagPercentage(.1f)
            .startingXPosition(50)
            .startingYPosition(50)
            .show(applicationContext)
    }
}