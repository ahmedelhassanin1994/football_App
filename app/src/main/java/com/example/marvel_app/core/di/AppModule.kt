package com.example.marvel_app.core.di

import android.content.Context
import androidx.room.Room
import com.example.marvel_app.core.Constant
import com.example.marvel_app.data.data_source_test.RemoteDataSource
import com.example.marvel_app.data.data_source_test.RemoteDataSourceImplementer
import com.example.marvel_app.data.data_source_test.localDataSource.AppDatabase
import com.example.marvel_app.data.data_source_test.localDataSource.Dao_Characters
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSource
import com.example.marvel_app.data.data_source_test.localDataSource.LocalDataSourceImplementer
import com.example.marvel_app.data.network.Api
import com.example.marvel_app.data.repository_test.RepositoryImpl
import com.example.marvel_app.domain.repository.Repository
import com.example.marvel_app.domain.usecase.Competition_UseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): Api {
        return retrofitBuilder
            .build()
            .create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "characters_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase) : Dao_Characters {
        return appDatabase.CharactersDao()
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api
    ) = RemoteDataSourceImplementer(api) as RemoteDataSource

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: Dao_Characters
    ) = LocalDataSourceImplementer(dao) as LocalDataSource

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) = RepositoryImpl(remoteDataSource,localDataSource) as Repository

    @Singleton
    @Provides
    fun provide_Usecase(
        repository: Repository
    ) = Competition_UseCase(repository)


}