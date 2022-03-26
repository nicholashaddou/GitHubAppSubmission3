package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.FavUserDao
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.FavoriteUser
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.UserDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var userDao: FavUserDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)
    init {
        userDao = userDb?.favUserDao()
    }
    fun getFavGitUser(): LiveData<List<FavoriteUser>>?{
        return userDao?.getFavUser()
    }
}