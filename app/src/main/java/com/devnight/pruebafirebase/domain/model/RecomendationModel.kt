package com.devnight.pruebafirebase.domain.model

import com.devnight.pruebafirebase.data.Recomendation
import com.devnight.pruebafirebase.data.database.entity.Recomendations

data class RecomendationModel(
                              val adult: Boolean,
                              val backdropPath: Any,
                              val genreIds: List<Int>,
                              val id: Int,
                              val originalLanguage: String,
                              val originalTitle: String,
                              val overview: String,
                              val popularity: Double,
                              val posterPath: Any,
                              val releaseDate: String,
                              val title: String,
                              val video: Boolean,
                              val voteAverage: Double,
                              val voteCount: Int)


fun Recomendation.toDomain() = RecomendationModel(adult ,backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)
fun Recomendations.toDomain() = RecomendationModel(adult ,backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)

