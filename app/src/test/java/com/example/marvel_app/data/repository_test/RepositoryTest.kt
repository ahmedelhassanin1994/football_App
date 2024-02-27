package com.example.marvel_app.data.repository_test

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import arrow.core.Either
import com.example.marvel_app.BuildConfig
import com.example.marvel_app.core.Network
import com.example.marvel_app.core.app.BaseApplication
import com.example.marvel_app.core.app.CryptoData
import com.example.marvel_app.data.MockApplication
import com.example.marvel_app.data.data_source_test.RemoteDataSource
import com.example.marvel_app.data.data_source_test.RemoteDataSourceImplementer
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSource
import com.example.marvel_app.data.network.Api
import com.example.marvel_app.data.network.Failure
import com.example.marvel_app.data.network.ResponseCode
import com.example.marvel_app.data.responeses.CompetitionsResponse
import com.example.marvel_app.domain.entities.CacheModel
import com.example.marvel_app.domain.repository.Repository
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Base64

class RepositoryTest {

    private val competitionsResponse = mock<CompetitionsResponse>()
    private val cachelist = mock<CacheModel>()

    @Mock
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var remoteDataSource: RemoteDataSource
    @Mock
    lateinit var repository: RepositoryImpl
    private val context = mock(Context::class.java)

    private var NetworkState=false

    @Before
    fun setup() {
        `when`(context.applicationContext).thenReturn(context)
        MockitoAnnotations.initMocks(this)
        repository = RepositoryImpl(remoteDataSource,localDataSource)
    }


    fun ShouldReturnNetworkState(value:Boolean){
        NetworkState=value
    }

    @Test
    fun `test getCompetitions with internet connection`() = runBlocking {
//        // Mocking remote data source response
        ShouldReturnNetworkState(true)
        if (NetworkState) {
            whenever(remoteDataSource.getCompetitions()).thenReturn(competitionsResponse)

            // Call the method being tested
            val result = remoteDataSource.getCompetitions()

            // Verify that remoteDataSource.getCompetitions() is called
            verify(remoteDataSource).getCompetitions()

            // Verify the result
            assertEquals(competitionsResponse, result)

        }
    }

    @Test
    fun `test getCompetitions without internet connection`() = runBlocking {
//        // Mocking remote data source response
        ShouldReturnNetworkState(false)
        if (!NetworkState) {

            whenever(localDataSource.getCache()).thenReturn(listOf(cachelist))

            // Call the method being tested
            val result = localDataSource.getCache()

            val cryptoData = CryptoData()
            val originalText = cryptoData.decrypt(Base64.getDecoder().decode(result.last().date.toString()))?.decodeToString()

            var gson = Gson()
            var charactersApiResponse = gson.fromJson(originalText, CompetitionsResponse::class.java)



            // Verify that localDataSource.getCache() is called
            verify(localDataSource).getCache()


            // Verify the result
            assertEquals(competitionsResponse, charactersApiResponse)
        }
    }


}