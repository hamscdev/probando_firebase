package com.devnight.pruebafirebase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devnight.pruebafirebase.data.database.dao.RecomendationDao
import com.devnight.pruebafirebase.data.database.entity.Recomendations


@Database(entities = [Recomendations::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun  recomendations(): RecomendationDao
}