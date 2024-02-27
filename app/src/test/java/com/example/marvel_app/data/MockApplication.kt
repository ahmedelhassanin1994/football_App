package com.example.marvel_app.data

import android.content.Context
import com.example.marvel_app.core.app.BaseApplication

class MockApplication : BaseApplication() {
    companion object {
        lateinit var mockContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mockContext = applicationContext
    }
}