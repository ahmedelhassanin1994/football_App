package com.example.marvel_app.data.data_source_test

import com.example.marvel_app.BuildConfig
import com.example.marvel_app.data.network.Api
import com.example.marvel_app.data.responeses.CompetitionsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class RemoteDataSourceTest {

    private val competitionsResponse = mock<CompetitionsResponse>()
    @Mock
    lateinit var api: Api
    lateinit var remoteDataSource: RemoteDataSource



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImplementer(api)
    }


    @Test
    fun `test getCompetitions`() = runBlocking {
        // Mock the behavior of your DAO method
        Mockito.`when`(api.getCompetitions(BuildConfig.Api_KEY)).thenReturn(competitionsResponse)

        // Call the method being tested
        val result = remoteDataSource.getCompetitions()

        // Verify the result
        Assert.assertEquals(competitionsResponse, result)
    }



}