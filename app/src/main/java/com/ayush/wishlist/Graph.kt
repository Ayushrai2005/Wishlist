package com.ayush.wishlist

import android.content.Context
import androidx.room.Room
import com.ayush.wishlist.Data.WishDatabase
import com.ayush.wishlist.Data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java , "wishlist.db").build()
    }


}