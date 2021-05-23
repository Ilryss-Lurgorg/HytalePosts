package com.example.hytaleposts.presentation

import android.app.Application
import android.content.Context

class HytalePostApplication : Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}