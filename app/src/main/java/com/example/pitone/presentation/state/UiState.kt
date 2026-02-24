package com.example.pitone.presentation.state

sealed class UiState<out T> {

    data object Loadig: UiState<Nothing>()

    data class Sucess<T>(val data: T): UiState<T>()

    data class Error(val message: String): UiState<Nothing>()

}
