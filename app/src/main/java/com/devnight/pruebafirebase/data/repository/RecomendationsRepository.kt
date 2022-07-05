package com.devnight.pruebafirebase.data.repository

import com.devnight.pruebafirebase.data.database.dao.RecomendationDao
import com.devnight.pruebafirebase.domain.model.RecomendationModel
import com.devnight.pruebafirebase.domain.model.toDomain
import com.devnight.pruebafirebase.core.network.ApiClient
import com.devnight.pruebafirebase.data.database.entity.Recomendations
import javax.inject.Inject

class RecomendationsRepository @Inject constructor(
    private val api: ApiClient, private val dao: RecomendationDao ) {


    suspend fun  getRecomendarionsFromApi(): List<RecomendationModel>{
        val response = api.getRecomendations().body()?.results
                    return response?.map { it.toDomain() } ?: emptyList()
    }
    suspend fun getRecomendationsFromDatabase():List<RecomendationModel> =
        dao.getAllRecomendations().map { it.toDomain() }

    suspend fun insertRecommendation(listRec: List<Recomendations>) {
       dao.insertAll(listRec)
    }
    suspend fun clearRecomendations(){
        dao.deleteAllRecomendations()
    }




}