package com.olvera.cronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.olvera.cronoapp.R
import com.olvera.cronoapp.components.CircleButton
import com.olvera.cronoapp.components.MainIconButton
import com.olvera.cronoapp.components.MainTitle
import com.olvera.cronoapp.components.timeFormat
import com.olvera.cronoapp.viewModel.ChronometerViewModel

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

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {

            // Start Button
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabled = !state.activeCrono
            ) {
                chronometerViewModel.start()
            }

            // Pause Button
            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enabled = state.activeCrono
            ) {
                chronometerViewModel.pause()
            }

            // Stop Button
            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enabled = !state.activeCrono
            ) {
                chronometerViewModel.stop()
            }

            // Save Button
            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enabled = state.showSaveButton
            ) {
                chronometerViewModel.showTextField()
            }
        }
    }
}