package com.example.dagger_exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagger_exam.di.RetroServiceInterface
import com.example.dagger_exam.model.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModel @Inject constructor(
    private val retrofitService: RetroServiceInterface
) : ViewModel() {
    private val _liveDataList: MutableLiveData<RecyclerList> = MutableLiveData()
    val liveDataList: LiveData<RecyclerList> get() = _liveDataList

    fun makeApiCall() {
        val call: Call<RecyclerList>? = retrofitService.getDataFromAPI("atl")

        call?.enqueue(object : Callback<RecyclerList> {
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                _liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    _liveDataList.postValue(response.body())
                } else {
                    _liveDataList.postValue(null)
                }
            }
        })
    }

    class Factory @Inject constructor(
        private val mainActivityViewModelProvider: Provider<MainActivityViewModel>
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return mainActivityViewModelProvider.get() as T
        }
    }
}