package com.example.marvel_app.presentation.details_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app.core.BaseApplication
import com.example.marvel_app.core.NetworkResult
import com.example.marvel_app.domain.usecase.Characters_UseCase
import com.example.marvel_app.domain.usecase.InputData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel  @Inject constructor(): ViewModel() {
//    val result = MutableStateFlow<NetworkResult<CharactersApiResponse>>(NetworkResult.Initial())





}