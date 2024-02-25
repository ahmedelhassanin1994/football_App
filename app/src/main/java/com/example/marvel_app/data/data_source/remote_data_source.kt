package com.example.marvel_app.data.data_source

import android.util.Log
import com.example.marvel_app.core.Constant
import com.example.marvel_app.core.getHash
import com.example.marvel_app.data.network.Api
import com.example.marvel_app.data.responeses.CompetitionsResponse
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getCompetitions(): CompetitionsResponse


}



class RemoteDataSourceImplementer
@Inject
constructor(
    private val api: Api

) : RemoteDataSource {
    override suspend fun getCompetitions(): CompetitionsResponse {
        Log.d("RemoteDataSource", "getCompetitions:")

        val data=api.getCompetitions()
        Log.d("RemoteDataSource", "getCompetitions:")

        return  data
    }


}