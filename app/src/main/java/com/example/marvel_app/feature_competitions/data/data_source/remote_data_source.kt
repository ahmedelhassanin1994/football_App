package com.example.marvel_app.data.data_source

import android.util.Log
import com.example.marvel_app.core.Constant
import com.example.marvel_app.data.network.Api
import com.example.marvel_app.data.responeses.CompetitionsResponse
import javax.inject.Inject
import com.example.marvel_app.BuildConfig
interface RemoteDataSource {

    suspend fun getCompetitions(): CompetitionsResponse


}



class RemoteDataSourceImplementer
@Inject
constructor(
    private val api: Api

) : RemoteDataSource {
    override suspend fun getCompetitions(): CompetitionsResponse {
        val key=BuildConfig.Api_KEY
        val data=api.getCompetitions(key)
        return  data
    }


}