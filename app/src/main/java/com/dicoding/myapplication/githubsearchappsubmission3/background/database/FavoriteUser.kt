package com.dicoding.myapplication.githubsearchappsubmission3.background.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_user")
data class FavoriteUser(
    @ColumnInfo(name = "loginID")
    val login: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "avatarURL")
    val avatar_url : String
)
