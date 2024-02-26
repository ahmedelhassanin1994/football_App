package com.example.marvel_app.presentation.home.widget

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marvel_app.core.resources.cardShape
import com.example.marvel_app.data.responeses.Competitions
import com.google.gson.Gson


@Composable
fun GridListTab(
    list: List<Competitions>,

    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        items(
            items = list,
            key = { it.id!! }
        ) { movie ->
            CardCompetitions(
                title = movie.name.toString(),
                subtittle = movie.area!!.name.toString(),
                imageUrl = "${movie.emblem}",
                onClickListener = {
                    val gson = Gson()
                    val json = gson.toJson(movie)
                    Log.d("MarvelGridList", "MarvelGridList:  ${json}")
                    navController.navigate("DetailsScreen?Competitions=${json}")

//                    navController.currentBackStackEntry?.arguments?.putSerializable("CharacterResult", movie.toString())
//                    navController.navigate("DetailsScreen")
                },
             modifier = modifier
                    .clip(cardShape)
                    .fillMaxWidth()
                    .height(272.dp)

            )
        }
    }
}