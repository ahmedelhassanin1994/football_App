package com.example.marvel_app.presentation.details_screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvel_app.core.common.CoilImage
import com.example.marvel_app.core.resources.BlackTransparent
import com.example.marvel_app.core.resources.MovieDetailsTextColor
import com.example.marvel_app.core.resources.MoviePrimaryBackgroundColor
import com.example.marvel_app.data.responeses.Competitions
import com.example.marvel_app.presentation.details_screen.DetailsViewModel

@Composable
fun DetailsScreenTablet(navController: NavController, data: Competitions?, viewModel: DetailsViewModel = hiltViewModel(), modifier: Modifier = Modifier){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MoviePrimaryBackgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            ) {
                CoilImage(
                    imageUrl = "${data?.emblem}",
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    BlackTransparent,
                                    MoviePrimaryBackgroundColor
                                )
                            )
                        )
                )


            }
            Column(
                modifier = modifier.padding(12.dp)
            ) {
                Text(
                    text = data?.name.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MovieDetailsTextColor,
                    modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                )

                Text(
                    text = data?.area?.name.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 25.sp,
                    color = Color.White,
                )


                Text(
                    text = data?.plan.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    color = Color.White,
                )

            }


        }

}

