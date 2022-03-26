package com.dicoding.myapplication.githubsearchappsubmission3.ui.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myapplication.githubsearchappsubmission3.databinding.ActivityFavoriteBinding
import com.dicoding.myapplication.githubsearchappsubmission3.background.database.FavoriteUser
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.adapters.UserAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavBinding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavBinding.root)

        supportActionBar?.title = "Favorite User"

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: com.dicoding.myapplication.githubsearchappsubmission3.background.UserData) {
                Intent(this@FavoriteActivity, UserDetail::class.java).also {
                    it.putExtra(UserDetail.EXTRA_USERNAME, data.login)
                    it.putExtra(UserDetail.EXTRA_ID, data.id)
                    it.putExtra(UserDetail.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }
        })
        activityFavBinding.apply {
            rvFavoriteuser.setHasFixedSize(true)
            rvFavoriteuser.adapter = adapter
            showRecyclerList()
        }
        viewModel.getFavGitUser()?.observe(this) {
            if (it!=null){
                val list = mapList(it)
                adapter.setList(list)
            }
        }
    }
    private fun mapList(users: List<FavoriteUser>): ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>{
        val listUsers = ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>()
        for (user in users) {
            val userMapped =
                com.dicoding.myapplication.githubsearchappsubmission3.background.UserData(
                    user.login,
                    user.avatar_url,
                    user.id

                )
            listUsers.add(userMapped)
        }
        return listUsers
    }
    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activityFavBinding.rvFavoriteuser.layoutManager = GridLayoutManager(this, 2)
        } else {
            activityFavBinding.rvFavoriteuser.layoutManager = LinearLayoutManager(this)
        }
        activityFavBinding.rvFavoriteuser.adapter = adapter
    }
}