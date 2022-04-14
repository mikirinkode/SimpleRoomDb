package com.mikirinkode.roomgame2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikirinkode.roomgame2.model.GameEntity

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(game: GameEntity)

    @Update
    fun update(game: GameEntity)

    @Delete
    fun delete(game: GameEntity)

    @Query("DELETE from game_table")
    fun deleteAllData()

    @Query("SELECT * FROM game_table ORDER BY ID ASC")
    fun getAllData(): LiveData<List<GameEntity>>
}