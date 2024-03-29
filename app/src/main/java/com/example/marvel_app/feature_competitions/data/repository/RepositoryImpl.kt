package com.example.marvel_app.data.repository_test

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import arrow.core.Either
import com.example.marvel_app.core.app.BaseApplication
import com.example.marvel_app.core.app.CryptoData
import com.example.marvel_app.core.Network
import com.example.marvel_app.data.data_source_test.RemoteDataSource
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSource
import com.example.marvel_app.data.network.Failure
import com.example.marvel_app.data.network.ResponseCode
import com.example.marvel_app.data.network.ResponseMessage
import com.example.marvel_app.data.responeses.CompetitionsResponse
import com.example.marvel_app.domain.entities.CacheModel
import com.example.marvel_app.domain.repository.Repository
import com.google.gson.Gson
import java.util.Base64
import javax.inject.Inject
class RepositoryImpl
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): Repository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCompetitions(): Either<Failure, CompetitionsResponse> {
        if (Network.checkConnectivity(BaseApplication.applicationContext())) {

            try {
                var response=remoteDataSource.getCompetitions()
                if (response !=null){
                    val gson = Gson()
                    val json = gson.toJson(response)
                    val bytes = json.toString().toByteArray()
                    val cryptoData = CryptoData()
                     val  data= Base64.getEncoder().encodeToString(cryptoData.encrypt(bytes = bytes))
                    localDataSource.insert(CacheModel(key = "Characters", date = "${data}"))
                    return Either.Right(response)
                }else{
                    return Either.Left(Failure(ResponseCode.BAD_REQUEST, ResponseMessage.BAD_REQUEST))
                }
            }catch (e:Exception){

                return Either.Left(Failure(ResponseCode.CONNECT_TIMEOUT, e.message.toString()))

            }
        }else{

            try {
                val data_characters=localDataSource.getCache()
                Log.d("MovieRepositoryImpl", " ${data_characters.last().date} ")
                val cryptoData = CryptoData()
                val originalText = cryptoData.decrypt(Base64.getDecoder().decode(data_characters.last().date.toString()))?.decodeToString()

                var gson = Gson()
                var charactersApiResponse = gson.fromJson(originalText, CompetitionsResponse::class.java)


                return Either.Right(charactersApiResponse)

            }catch (e:Exception){
                return Either.Left(Failure(ResponseCode.NI_INTERNET_CONNECTION,  ResponseMessage.BAD_REQUEST))
            }



        }

    }


}