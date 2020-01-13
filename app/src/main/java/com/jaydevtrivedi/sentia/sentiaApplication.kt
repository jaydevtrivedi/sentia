package com.jaydevtrivedi.sentia

import android.app.Application

class sentiaApplication : Application() {
    val dataSource
        get() = ServiceLocator.provideDataSource()
}