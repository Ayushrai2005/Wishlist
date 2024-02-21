package com.ayush.wishlist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L ,
    @ColumnInfo("Wish-title")
    val title : String = "",
    @ColumnInfo("Wish-desc")
    val description : String = ""
)