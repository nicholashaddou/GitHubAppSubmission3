package com.dicoding.myapplication.githubsearchappsubmission3.background.database

import com.dicoding.myapplication.githubsearchappsubmission3.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

        @GET("search/users")
        @Headers("Authorization: token ${BuildConfig.GITHUB}")
        fun getGithubUsers(@Query("q") query: String): Call<GitUserResponse>

        @GET("users/{username}")
        @Headers("Authorization: token ${BuildConfig.GITHUB}")
        fun getGithubFullname(
            @Path("username") username: String
        ): Call<UserDetailResponse>

        @GET("users/{username}/following")
        @Headers("Authorization: token ${BuildConfig.GITHUB}")
        fun getGithubFollowerList(
            @Path("username") username: String
        ):Call<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>

        @GET("users/{username}/followers")
        @Headers("Authorization: token ${BuildConfig.GITHUB}")
        fun getGithubFollowingList(
            @Path("username") username: String
        ):Call<ArrayList<com.dicoding.myapplication.githubsearchappsubmission3.background.UserData>>



}