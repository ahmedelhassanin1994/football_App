package com.example.marvel_app.data.data_source_test

import com.example.marvel_app.data.data_source_test.localDataSource.Dao_Characters
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSource
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSourceImplementer
import com.example.marvel_app.domain.entities.CacheModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class LocalDataSourceTest {

    // Mocking the dependencies
    @Mock
    lateinit var charactersDao: Dao_Characters // Assuming you have a DAO interface

    lateinit var localdatasource: LocalDataSource
    private val charactersCache = mock<CacheModel>()
    private val cacheModellist = mock<List<CacheModel>>()
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        localdatasource = LocalDataSourceImplementer(charactersDao)
    }

    @Test
    fun `test getCache`() = runBlocking {
        // Mock the behavior of your DAO method
        `when`(charactersDao.getCache()).thenReturn(cacheModellist)

        // Call the method being tested
        val result = localdatasource.getCache()

        // Verify the result
        assertEquals(cacheModellist, result)
    }

    @Test
    fun `test insert`() = runBlocking {


        // Call the method being tested
        localdatasource.insert(charactersCache)

        // Verify that the DAO method was called with the correct parameter
        verify(charactersDao).insertCache(charactersCache)
    }

    @Test
    fun `test delete`() = runBlocking {

        // Call the method being tested
        localdatasource.delete(charactersCache)

        // Verify that the DAO method was called with the correct parameter
        verify(charactersDao).deleteCache(charactersCache)
    }

}