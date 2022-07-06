package com.devnight.pruebafirebase.core.network

import com.devnight.pruebafirebase.data.RecomendationMovie
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("discover/movie/")
    suspend fun getRecomendations(@Query("api_key") apiKey: String): Response<RecomendationMovie>
}