package com.example.marvel_app.data.repository

import android.util.Log
import arrow.core.Either
import com.example.marvel_app.core.BaseApplication
import com.example.marvel_app.core.Network
import com.example.marvel_app.data.data_source.RemoteDataSource
import com.example.marvel_app.data.data_source.localDataSource.LocalDataSource
import com.example.marvel_app.data.network.ApiInternalStatus
import com.example.marvel_app.data.network.Failure
import com.example.marvel_app.data.network.ResponseCode
import com.example.marvel_app.data.network.ResponseMessage
import com.example.marvel_app.data.responeses.CompetitionsResponse
import com.example.marvel_app.domain.entities.CharactersCache
import com.example.marvel_app.domain.repository.Repository
import com.google.gson.Gson
import javax.inject.Inject
class MovieRepositoryImpl
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): Repository {
    override suspend fun getCompetitions(): Either<Failure, CompetitionsResponse> {
        if (Network.checkConnectivity(BaseApplication.applicationContext())) {

            try {
                var response=remoteDataSource.getCompetitions()
                Log.d("MovieRepositoryImpl", "MovieRepositoryImpl: ${response} ")

                if (response !=null){
                    val gson = Gson()
                    val json = gson.toJson(response)


                    localDataSource.insert(CharactersCache(key = "Characters", date = "${json}"))
                    return Either.Right(response)
                }else{

                    return Either.Left(Failure(ResponseCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST))
                }
            }catch (e:Exception){
                Log.d("MovieRepositoryImpl", "MovieRepositoryImpl: ${e.message} ")

                return Either.Left(Failure(ResponseCode.CONNECT_TIMEOUT, e.message.toString()))

            }
        }else{

            try {
                val data_characters=localDataSource.getCharacters()
                Log.d("MovieRepositoryImpl", "${data_characters.last().date} ")


                var gson = Gson()
                var charactersApiResponse = gson.fromJson(data_characters.last().date, CompetitionsResponse::class.java)


                return Either.Right(charactersApiResponse)

            }catch (e:Exception){
                return Either.Left(Failure(ResponseCode.NI_INTERNET_CONNECTION,  ResponseMessage.BAD_REQUEST))
            }



        }

    }


}