package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.myapplication.githubsearchappsubmission3.R
import com.dicoding.myapplication.githubsearchappsubmission3.databinding.ActivityUserDetailBinding
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.adapters.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*

@InternalCoroutinesApi
class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following
        )
    }
    private lateinit var userDetailBinding: ActivityUserDetailBinding
    private lateinit var userDetailViewmodel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        userDetailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(userDetailBinding.root)

        supportActionBar?.hide()

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showProgressBar(true)

        userDetailViewmodel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        userDetailViewmodel .setGithubUsersDetail(username!!)
        userDetailViewmodel.getGithubUsersDetail().observe(this) {
            if(it != null){
                userDetailBinding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvCompany.text = it.company
                    followers.text = "${it.followers} Followers"
                    following.text = "${it.following} Following"
                    showProgressBar(false)
                    Glide.with(this@UserDetail)
                        .load(it.avatar_url)
                        .into(ivAvatar)
                }
            }
        }

        var checked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = userDetailViewmodel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count > 0 ){
                        userDetailBinding.toggleFav.isChecked = true
                        checked = true
                    }else{
                        userDetailBinding.toggleFav.isChecked = false
                        checked = false
                    }
                }
            }
        }
        userDetailBinding.toggleFav.setOnClickListener {
            checked = !checked
            if (checked){
                userDetailViewmodel.addUserToFav(username,id,avatarUrl!!)
            }else{
                userDetailViewmodel.removeFromFav(id)
            }
            userDetailBinding.toggleFav.isChecked = checked
        }
        val pagerAdapter = SectionsPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.vp_android)
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = findViewById(R.id.tl_github)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
    private fun showProgressBar(state: Boolean) {
        if (state) {
            userDetailBinding.progressBar.visibility = View.VISIBLE
        } else {
            userDetailBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}

