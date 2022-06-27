package com.example.dagger_exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dagger_exam.di.RetroServiceInterface
import com.example.dagger_exam.model.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    @Inject
    lateinit var mService : RetroServiceInterface

    private lateinit var liveDataList: MutableLiveData<RecyclerList>


    init{
        //here we need to init application.
        (application as MyApplication).getRetroComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<RecyclerList>{
        return liveDataList
    }

    fun makeApicall(){
       val call : Call<RecyclerList>? = mService.getDataFromAPI("atl")
        call?.enqueue(object : Callback<RecyclerList>{
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
               if(response.isSuccessful){
                   liveDataList.postValue(response.body())
               }else{
                   liveDataList.postValue(null)
               }

            }

        })
    }
}