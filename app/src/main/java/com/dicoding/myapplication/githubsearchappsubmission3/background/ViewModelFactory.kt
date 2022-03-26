package com.dicoding.myapplication.githubsearchappsubmission3.background

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.UserViewModel

class ViewModelFactory(private val pref: com.dicoding.myapplication.githubsearchappsubmission3.background.SwitchPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}