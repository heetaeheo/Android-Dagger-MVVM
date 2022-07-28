package com.example.dagger_exam

import android.app.Application
import com.example.dagger_exam.di.DaggerRetroComponent

import com.example.dagger_exam.di.RetroComponent
import com.example.dagger_exam.di.RetroModule

class MyApplication : Application() {
    lateinit var retroComponent: RetroComponent
        private set

    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }
}