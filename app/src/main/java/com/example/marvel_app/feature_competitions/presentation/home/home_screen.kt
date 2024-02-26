package com.example.marvel_app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvel_app.core.NetworkResult
import com.example.marvel_app.core.common.WindowInfo
import com.example.marvel_app.core.common.rememberWindowInfo
import com.example.marvel_app.core.resources.MoviePrimaryBackgroundColor
import com.example.marvel_app.presentation.home.widget.GridListMobile
import com.example.marvel_app.presentation.home.widget.GridListTab



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(),modifier: Modifier = Modifier,navController: NavController) {
    val result by viewModel.result.collectAsState()
    val result_search = viewModel.list_search
    var text by remember{ mutableStateOf("") }
    var IsSearch by remember{ mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MoviePrimaryBackgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){




            when (result) {
                is NetworkResult.Initial -> {
                    Text(text = "Search for a character")
                }

                is NetworkResult.Success -> {
                    val windowinfo=rememberWindowInfo()

                    if (windowinfo.ScreenWidthInfo is WindowInfo.WindowType.Compact) {

                        GridListMobile(
                            list = result.data?.competitions!!,
                            navController = navController

                        )
                    }else{
                        GridListTab(
                            list = result.data?.competitions!!,
                            navController = navController
                        )
                    }

                }

                is NetworkResult.Loading -> {
                    Box(modifier.fillMaxSize()) {
                        CircularProgressIndicator(

                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 24.dp)
                        )
                    }
                }

                is NetworkResult.Error -> {
                    Text(text = "Error: ")
                }
            }


        }


    }





