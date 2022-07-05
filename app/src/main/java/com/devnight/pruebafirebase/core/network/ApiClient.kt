package com.devnight.pruebafirebase.core.network

import com.devnight.pruebafirebase.data.RecomendationMovie
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("discover/movie/")
    suspend fun getRecomendations() : Response<RecomendationMovie>
}