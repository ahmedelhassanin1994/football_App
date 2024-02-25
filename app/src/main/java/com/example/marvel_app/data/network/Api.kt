package com.example.marvel_app.data.network

import com.example.marvel_app.data.responeses.CompetitionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v4/competitions")
    suspend fun getCompetitions(): CompetitionsResponse

}