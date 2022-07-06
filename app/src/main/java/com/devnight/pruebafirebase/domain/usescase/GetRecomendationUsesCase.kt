package com.devnight.pruebafirebase.domain.usescase

import com.devnight.pruebafirebase.data.database.entity.toDatabase
import com.devnight.pruebafirebase.data.repository.RecomendationsRepository
import com.devnight.pruebafirebase.domain.model.RecomendationModel
import javax.inject.Inject

class GetRecomendationUsesCase @Inject constructor(
    private val repository: RecomendationsRepository) {

    suspend operator fun invoke():List<RecomendationModel> {
        var quotes = repository.getRecomendarionsFromApi("914285347772e4354e16550039ed0340")
        return if (quotes.isNotEmpty()){
            repository.clearRecomendations()
            repository.insertRecommendation(quotes.map{it.toDatabase()})
            quotes
        }else{
            repository.getRecomendationsFromDatabase()
        }
    }
}