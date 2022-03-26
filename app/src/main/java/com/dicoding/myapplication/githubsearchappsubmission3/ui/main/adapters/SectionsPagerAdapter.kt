package com.dicoding.myapplication.githubsearchappsubmission3.ui.main.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.fragments.FollowersFragment
import com.dicoding.myapplication.githubsearchappsubmission3.ui.main.fragments.FollowingFragment
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class SectionsPagerAdapter(
    activity: AppCompatActivity,
    data: Bundle,
) : FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data
    init{
        fragmentBundle = data
    }
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position){
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowersFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }
}