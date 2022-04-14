package com.mikirinkode.roomgame2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "game_table")
data class GameEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    val genre: String,
    val price: Int,
): Parcelable
