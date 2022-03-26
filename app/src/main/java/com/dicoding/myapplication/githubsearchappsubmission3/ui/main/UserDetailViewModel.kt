package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InternalCoroutinesApi
class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: FavUserDao?
    private var userDb: UserDatabase
    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb.favUserDao()
    }
    val listUsersDetail = MutableLiveData<UserDetailResponse>()

    fun setGithubUsersDetail(username: String) {
        ApiConfig.getGithubApi()
            .getGithubFullname(username)
            .enqueue(object : Callback<UserDetailResponse> {
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    detailResponse: Response<UserDetailResponse>
                ) {
                    if (detailResponse.isSuccessful) {
                        listUsersDetail.postValue(detailResponse.body())
                    }
                }
                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                }

            })
    }
    fun getGithubUsersDetail(): LiveData<UserDetailResponse> {
        return listUsersDetail
    }
    fun addUserToFav(username: String, id:Int, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            val users = FavoriteUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFav(users)
        }
    }
    suspend fun checkUser(id:Int) = userDao?.checkUser(id)
    fun removeFromFav(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFav(id)
        }
    }
}