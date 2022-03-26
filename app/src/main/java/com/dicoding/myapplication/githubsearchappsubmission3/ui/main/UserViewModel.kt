package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import androidx.lifecycle.*
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.ApiConfig
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.GitUserResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val pref: com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences) : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>()

    fun setGithubUsers(query: String) {
        ApiConfig.getGithubApi()
            .getGithubUsers(query)
            .enqueue(object : Callback<GitUserResponse> {
                override fun onResponse(
                    call: Call<GitUserResponse>,
                    response: Response<GitUserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<GitUserResponse>, t: Throwable) {
                }

            })
    }
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
    fun getGithubUsers(): LiveData<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>> {
        return listUsers
    }
}