package com.olvera.cronoapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.cronoapp.repository.CronosRepository
import com.olvera.cronoapp.state.ChronometerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronometerViewModel @Inject constructor(
    private val repository: CronosRepository
) : ViewModel() {

    var state by mutableStateOf(ChronometerState())
        private set

    var chronoJob by mutableStateOf<Job?>(null)
        private set

    var time by mutableLongStateOf(0L)
        private set

    fun getChronoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCronoById(id).collect { item ->
                time = item.crono
                state = state.copy(
                    title = item.title
                )
            }
        }
    }

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