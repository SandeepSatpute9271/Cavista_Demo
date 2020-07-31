package com.mefy.cavista_demo

import android.app.Application
import io.realm.Realm

class CavitaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}