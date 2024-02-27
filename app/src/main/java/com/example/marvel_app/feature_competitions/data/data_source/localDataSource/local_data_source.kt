package com.example.marvel_app.data.data_source_test.localDataSource


import com.example.marvel_app.domain.entities.CacheModel

import javax.inject.Inject

interface LocalDataSource {

    suspend fun getCache(): List<CacheModel>
    suspend fun insert(characters : CacheModel)
    suspend fun delete(characters : CacheModel)
}



class LocalDataSourceImplementer
@Inject
constructor(
    private val dao_characters: Dao_Characters

) : LocalDataSource {
    override suspend fun getCache():List<CacheModel> {

        return dao_characters.getCache()
    }

    override suspend fun insert(characters: CacheModel) {
        dao_characters.insertCache(characters)
    }

    override suspend fun delete(characters: CacheModel) {
      dao_characters.deleteCache(characters)
    }


}