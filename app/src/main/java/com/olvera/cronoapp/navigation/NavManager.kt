package com.olvera.cronoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olvera.cronoapp.views.AddView
import com.olvera.cronoapp.views.EditView
import com.olvera.cronoapp.views.HomeView

@Composable
fun NavManger() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home" ) {
        composable("Home") {
            HomeView(navController = navController)
        }

        composable("AddView") {
            AddView(navController = navController)
        }
        
        composable("EditView") {
            EditView(navController = navController)
        }
    }
}