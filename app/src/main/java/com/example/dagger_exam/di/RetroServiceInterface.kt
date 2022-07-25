package com.example.dagger_exam.di


import com.example.dagger_exam.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {
    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<RecyclerList>?
}