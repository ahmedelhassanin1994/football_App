package com.example.marvel_app.domain.usecase

import arrow.core.Either
import com.example.marvel_app.core.app.BaseCase
import com.example.marvel_app.data.network.Failure
import com.example.marvel_app.data.responeses.CompetitionsResponse
import com.example.marvel_app.domain.repository.Repository

class Competition_UseCase(private val repository : Repository) :
    BaseCase<InputData, CompetitionsResponse> {

    override suspend fun execute(input: InputData): Either<Failure, CompetitionsResponse> {
        return repository.getCompetitions()
    }
}

 class  InputData(){

 }