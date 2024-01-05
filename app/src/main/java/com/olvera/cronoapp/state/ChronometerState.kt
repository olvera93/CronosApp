package com.olvera.cronoapp.state

data class ChronometerState(
    val activeCrono: Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField: Boolean = false,
    val title: String = ""
)
