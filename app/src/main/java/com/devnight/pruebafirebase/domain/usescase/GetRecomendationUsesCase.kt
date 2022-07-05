package com.devnight.pruebafirebase.domain.usescase

import com.devnight.pruebafirebase.data.database.entity.toDatabase
import com.devnight.pruebafirebase.data.repository.RecomendationsRepository
import com.devnight.pruebafirebase.domain.model.RecomendationModel
import javax.inject.Inject

class GetRecomendationUsesCase @Inject constructor(private val repository: RecomendationsRepository) {

    suspend operator fun getRecomendations():List<RecomendationModel> {
        val quotes = repository.getRecomendarionsFromApi()
        return if (quotes.isNotEmpty()){
            repository.clearRecomendations()
            repository.insertRecommendation(quotes.map{it.toDatabase()})
            quotes
        }else{
            repository.getRecomendationsFromDatabase()
        }
    }



}