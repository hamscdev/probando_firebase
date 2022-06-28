package com.devnight.pruebafirebase.data.repository

import com.devnight.pruebafirebase.data.database.dao.RecomendationDao
import com.devnight.pruebafirebase.domain.model.RecomendationModel
import com.devnight.pruebafirebase.domain.model.toDomain
import com.devnight.pruebafirebase.domain.network.ApiClient
import javax.inject.Inject

class RecomendationsRepository @Inject constructor(
    private val api: ApiClient, private val dao: RecomendationDao ) {


    suspend fun  getRecomendarionsFromApi(): List<RecomendationModel>{
        val response = api.getRecomendations().body()?.results
        if (response != null) {
            return response.map { it.toDomain() }
        }
    }









}