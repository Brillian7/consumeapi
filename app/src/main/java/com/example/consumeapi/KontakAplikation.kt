package com.example.consumeapi

import android.app.Application
import com.example.consumeapi.repositori.AppContainer
import com.example.consumeapi.repositori.KontakContainer

class KontakAplikation : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}