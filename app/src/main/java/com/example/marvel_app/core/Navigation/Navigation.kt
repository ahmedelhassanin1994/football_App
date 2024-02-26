package com.example.marvel_app.core.Navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvel_app.data.responeses.Competitions
import com.example.marvel_app.presentation.details_screen.DetailsScreen
import com.example.marvel_app.presentation.home.HomeScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController =navController , startDestination = "Home"  ){

        composable(route = "Home") {
            HomeScreen(navController = navController )
        }

        composable(route = "DetailsScreen?Competitions={Competitions}",
            arguments = listOf(
                navArgument(name = "Competitions"){
                    type= NavType.StringType
                    defaultValue=""
                },
            )

            ) {backStack  ->
            val gson: Gson = GsonBuilder().create()
            /* Extracting the user object json from the route */
           val userJson = backStack.arguments?.getString("Competitions")
            // Convert json string to the User data class object
            Log.d("Navigation", "Navigation: ${userJson}")
            val competitions = gson.fromJson(userJson, Competitions::class.java)
            DetailsScreen(navController = navController, data = competitions)

        }


    }
}

