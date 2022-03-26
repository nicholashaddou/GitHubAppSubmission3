package com.dicoding.myapplication.githubsearchappsubmission3.background.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [FavoriteUser::class], version = 2, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    @InternalCoroutinesApi
    companion object{
        private var INSTANCE : UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase{
           INSTANCE?:synchronized(this){
                    INSTANCE = databaseBuilder(context.applicationContext, UserDatabase::class.java,"user_database")
                        .fallbackToDestructiveMigration()
                        .build()
           }
            return INSTANCE as UserDatabase
        }
    }
    abstract fun favUserDao(): FavUserDao
}