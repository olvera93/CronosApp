package com.olvera.cronoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        composable(
            "EditView/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType }
            )) {
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, chronometerViewModel, cronosViewModel, id)
        }
    }
}