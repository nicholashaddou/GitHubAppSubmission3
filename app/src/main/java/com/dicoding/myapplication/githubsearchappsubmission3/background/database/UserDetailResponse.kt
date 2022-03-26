package com.dicoding.myapplication.githubsearchappsubmission3.background.database

data class UserDetailResponse(
    val login: String,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val company: String,
    val location: String,
    val followers: Int,
    val following: Int,
    val public_repos: Int
)
