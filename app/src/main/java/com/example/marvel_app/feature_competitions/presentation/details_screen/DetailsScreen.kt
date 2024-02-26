package com.example.marvel_app.presentation.details_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvel_app.core.common.WindowInfo
import com.example.marvel_app.core.common.rememberWindowInfo
import com.example.marvel_app.data.responeses.Competitions
import com.example.marvel_app.presentation.details_screen.widget.DetailsScreenMobile
import com.example.marvel_app.presentation.details_screen.widget.DetailsScreenTablet

@Composable
fun DetailsScreen(navController: NavController, data: Competitions?, viewModel: DetailsViewModel = hiltViewModel(), modifier: Modifier = Modifier){
    val windowinfo= rememberWindowInfo()

    if (windowinfo.ScreenWidthInfo is WindowInfo.WindowType.Compact) {
        DetailsScreenMobile(navController=navController,data=data,viewModel=viewModel)

    }else{
        DetailsScreenTablet(navController=navController,data=data,viewModel=viewModel)


    }






}
