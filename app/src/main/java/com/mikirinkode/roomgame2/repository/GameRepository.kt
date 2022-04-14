package com.mikirinkode.roomgame2.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mikirinkode.roomgame2.database.GameDao
import com.mikirinkode.roomgame2.database.GameDatabase
import com.mikirinkode.roomgame2.model.GameEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GameRepository(application: Application) {
    private val gameDao: GameDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GameDatabase.getDatabase(application)
        gameDao = db.gameDao()
    }

    fun getAllData(): LiveData<List<GameEntity>> = gameDao.getAllData()

    fun insert(game: GameEntity){
        executorService.execute{ gameDao.insert(game) }
    }

    fun update(game: GameEntity){
        executorService.execute{ gameDao.update(game) }
    }

    fun delete(game: GameEntity){
        executorService.execute { gameDao.delete(game) }
    }

    fun deleteAllData(){
        executorService.execute { gameDao.deleteAllData() }
    }
}