package com.example.marvel_app.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app.core.NetworkResult
import com.example.marvel_app.data.responeses.Competitions
import com.example.marvel_app.data.responeses.CompetitionsResponse
import com.example.marvel_app.domain.usecase.Competition_UseCase
import com.example.marvel_app.domain.usecase.InputData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(private val competitionUsecase: Competition_UseCase): ViewModel() {
    val result = MutableStateFlow<NetworkResult<CompetitionsResponse>>(NetworkResult.Initial())
    var list_search: List<Competitions> = listOf()

    init {

        getCharacters()
    }
     fun getCharacters() {

         result.value = NetworkResult.Loading()
        viewModelScope.launch {
            competitionUsecase.execute(InputData()).fold(
                {
                    result.value = NetworkResult.Error(it.message)
                },{
                    result.value = NetworkResult.Success(it)
//                    result.value.data?.data?.results=list
                }
            )
        }
    }

    fun addSearchedFOrItemsToSearchedList(search:String){
        list_search= result.value.data?.competitions!!.filter {
            it.name!!.startsWith(search)
        }
    }
}