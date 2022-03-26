package com.dicoding.myapplication.githubsearchappsubmission3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        Handler().postDelayed({
            val listIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(listIntent)
            finish()
        },2000L)
    }
}