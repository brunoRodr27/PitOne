package com.example.pitone.presentation.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitone.domain.model.Driver
import com.example.pitone.domain.repository.DriverRepository
import com.example.pitone.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DriverUiState(
    val drivers: List<Driver> = emptyList(),
    val favoriteDriverNumber: Set<Int> = emptySet(),
    val uiState: UiState<List<Driver>> = UiState.Loadig,
    val selectedDriver: Driver? = null
)

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val repository: DriverRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(DriverUiState())
    val uiState: StateFlow<DriverUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeFavorites().collect { favorites ->
                _uiState.update { it.copy(favoriteDriverNumber = favorites) }
            }
            loadDrivers()
        }
    }

    fun loadDrivers() {
        viewModelScope.launch {
            _uiState.update { it.copy(uiState = UiState.Loadig) }
            repository.getDrivers()
                .onSuccess { drivers ->
                    _uiState.update {
                        it.copy(
                            drivers = drivers,
                            uiState = UiState.Sucess(drivers)
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            uiState = UiState.Error(error.message ?: "Erro ao carregar pilotos")
                        )
                    }
                }
        }
    }

    fun toggleFavorite(driver: Driver) {
        viewModelScope.launch {
            repository.toggleFavorite(driver)
        }
    }

    fun selectDriver(driver: Driver?) {
        _uiState.update { it.copy(selectedDriver = driver) }
    }

}