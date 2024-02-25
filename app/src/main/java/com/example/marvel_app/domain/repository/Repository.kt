package com.example.marvel_app.domain.repository

import arrow.core.Either
import com.example.marvel_app.data.network.Failure
import com.example.marvel_app.data.responeses.CompetitionsResponse

interface Repository {

    suspend fun getCompetitions(): Either<Failure, CompetitionsResponse>


}