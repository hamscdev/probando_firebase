package com.devnight.pruebafirebase.domain.network

import com.devnight.pruebafirebase.data.RecomendationMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("discover/movie/")
    suspend fun getRecomendations() : Response<RecomendationMovie>
}