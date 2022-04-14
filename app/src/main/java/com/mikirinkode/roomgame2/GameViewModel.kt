package com.mikirinkode.roomgame2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mikirinkode.roomgame2.model.GameEntity
import com.mikirinkode.roomgame2.repository.GameRepository

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val repository = GameRepository(application)

    fun getAllData(): LiveData<List<GameEntity>> = repository.getAllData()

    fun insert(gameEntity: GameEntity){
        repository.insert(gameEntity)
    }

    fun update(gameEntity: GameEntity){
        repository.update(gameEntity)
    }

    fun delete(gameEntity: GameEntity){
        repository.delete(gameEntity)
    }

    fun deleteAllData(){
        repository.deleteAllData()
    }
}