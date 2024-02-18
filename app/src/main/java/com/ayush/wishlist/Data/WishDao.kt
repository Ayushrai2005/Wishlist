package com.ayush.wishlist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        abstract fun addAWish(wish : Wish)


        //Loads All wishes from thr wish table
        @Query("SELECT * FROM 'Wish-table'")
        abstract suspend fun getAllWishes() : Flow<List<Wish>>

        @Update
        abstract suspend fun updateAWish(wishEntity : Wish)


        @Query("SELECT * FROM 'Wish-table' WHERE ID=id")
        abstract fun getAWishById(id:Long) : Flow<Wish>


        @Delete
        abstract fun deleteAWish(wishEntity: Wish)

}