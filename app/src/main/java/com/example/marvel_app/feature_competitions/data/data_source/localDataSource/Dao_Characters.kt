package com.example.marvel_app.data.data_source_test.localDataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel_app.domain.entities.CacheModel


@Dao
interface Dao_Characters {

    @Query("SELECT * FROM characters_cache")
    suspend fun getCache(): List<CacheModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(task: CacheModel)

    @Delete
    suspend fun deleteCache(task: CacheModel)

}