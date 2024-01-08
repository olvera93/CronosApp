package com.olvera.cronoapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.cronoapp.state.ChronometerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChronometerViewModel: ViewModel() {

    var state by mutableStateOf(ChronometerState())
        private set

    var chronoJob by mutableStateOf<Job?>(null)
        private set

    var time by mutableStateOf(0L)
        private set

    fun onValue(value: String) {
        state = state.copy(title = value)
    }

    fun start() {
        state = state.copy(
            activeCrono = true
        )
    }

    fun pause() {
        state = state.copy(
            activeCrono = false,
            showSaveButton = true
        )
    }

    fun stop() {
        chronoJob?.cancel()
        time = 0
        state = state.copy(
            activeCrono = false,
            showSaveButton = false,
            showTextField = false
        )
    }

    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }

    fun chrono() {
        if (state.activeCrono) {
            chronoJob?.cancel()
            chronoJob = viewModelScope.launch {
                while (true) {
                    delay(1_000L)
                    time += 1000
                }
            }
        } else {
            chronoJob?.cancel()
        }
    }
}