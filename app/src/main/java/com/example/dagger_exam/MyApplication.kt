package com.example.dagger_exam

import android.app.Application
import com.example.dagger_exam.di.DaggerRetroComponent

import com.example.dagger_exam.di.RetroComponent
import com.example.dagger_exam.di.RetroModule



class MyApplication : Application(){


    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent {
        return retroComponent
    }
}