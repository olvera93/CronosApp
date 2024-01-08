package com.olvera.cronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.olvera.cronoapp.components.ChronCard
import com.olvera.cronoapp.components.FloatButton
import com.olvera.cronoapp.components.MainTitle
import com.olvera.cronoapp.components.timeFormat
import com.olvera.cronoapp.viewModel.ChronometerViewModel
import com.olvera.cronoapp.viewModel.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    chronometerViewModel: CronosViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "CRONO APP") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {

        ContentHomeView(it, navController, chronometerViewModel)

    }
}

@Composable
fun ContentHomeView(
    it: PaddingValues,
    navController: NavController,
    chronometerViewModel: CronosViewModel
) {
    Column(
        modifier = Modifier.padding(it)
    ) {

        val chronosList by chronometerViewModel.cronosList.collectAsState()

        LazyColumn() {
            items(chronosList) { item ->
                ChronCard(
                    title = item.title,
                    chrono = timeFormat(item.crono)
                ) {}
            }
        }

    }
}