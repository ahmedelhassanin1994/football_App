package com.example.marvel_app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marvel_app.data.responeses.Competitions



@Composable
fun MarvelGridList(
    list: List<Competitions>,

    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        items(
            items = list,
            key = { it.id!! }
        ) { movie ->
            MovieCard(
                title = movie.name.toString(),
                subtittle = movie.area!!.name.toString(),
                imageUrl = "${movie.emblem}",
                onClickListener = {

                    navController.navigate("DetailsScreen?id=${movie.id}")

//                    navController.currentBackStackEntry?.arguments?.putSerializable("CharacterResult", movie.toString())
//                    navController.navigate("DetailsScreen")
                }
            )
        }
    }
}