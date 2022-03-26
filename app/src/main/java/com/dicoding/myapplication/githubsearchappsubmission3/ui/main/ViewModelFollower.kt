package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollower: ViewModel() {
    val followerList = MutableLiveData<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>()
    fun setFollowerList(username: String){
        ApiConfig.getGithubApi()
            .getGithubFollowerList(username)
            .enqueue(object : Callback<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>{
                override fun onResponse(
                    call: Call<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>,
                    response: Response<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>
                ) {
                    if (response.isSuccessful){
                        followerList.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>, t: Throwable) {
                }

            })
    }
    fun getFollowerList(): LiveData<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>{
        return followerList
    }
}