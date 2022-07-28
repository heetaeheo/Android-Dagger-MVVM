package com.example.dagger_exam.di

import com.example.dagger_exam.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(activity: MainActivity)
}