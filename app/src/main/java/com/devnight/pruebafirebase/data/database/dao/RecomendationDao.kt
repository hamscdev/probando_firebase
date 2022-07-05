package com.devnight.pruebafirebase.data.database.dao

import androidx.room.*
import com.devnight.pruebafirebase.data.database.entity.Recomendations


@Dao
interface RecomendationDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(args: List<Recomendations>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecomendations(args: Recomendations)
    @Update
    suspend fun updateRecomendations(args: Recomendations)
    @Delete
    suspend fun deleteRecomendations(args: Recomendations)

    @Query("SELECT * FROM tbl_discover")
    suspend fun getAllRecomendations(): List<Recomendations>

    @Query("DELETE FROM tbl_discover")
    suspend fun deleteAllRecomendations()
}