package com.olvera.cronoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olvera.cronoapp.viewModel.ChronometerViewModel
import com.olvera.cronoapp.viewModel.CronosViewModel
import com.olvera.cronoapp.views.AddView
import com.olvera.cronoapp.views.EditView
import com.olvera.cronoapp.views.HomeView

@Composable
fun NavManager(
    chronometerViewModel: ChronometerViewModel,
    cronosViewModel: CronosViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, cronosViewModel)
        }

        composable("AddView") {
            AddView(navController, chronometerViewModel, cronosViewModel)
        }

        composable("EditView") {
            EditView(navController)
        }
    }
}