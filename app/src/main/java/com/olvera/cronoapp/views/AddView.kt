package com.olvera.cronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.olvera.cronoapp.components.FloatButton
import com.olvera.cronoapp.components.MainIconButton
import com.olvera.cronoapp.components.MainTitle
import com.olvera.cronoapp.components.timeFormat
import com.olvera.cronoapp.viewModel.ChronometerViewModel
import com.olvera.cronoapp.viewModel.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, chronometerViewModel: ChronometerViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "ADD CRONO") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {

        ContentAddView(it, navController, chronometerViewModel)

    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    chronometerViewModel: ChronometerViewModel
) {

    val state = chronometerViewModel.state

    LaunchedEffect(state.activeCrono) {
        chronometerViewModel.chrono()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = timeFormat(chronometerViewModel.time),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = { chronometerViewModel.start() }) {
            Text(text = "Start")
        }
    }
}