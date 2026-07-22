package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.usecase.GetGastoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoListViewModel @Inject constructor(
    private val getGastoUseCase: GetGastoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GastoListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadGastos()
    }

    fun onEvent(event: GastoListUiEvent) {
        when (event) {
            GastoListUiEvent.Refresh -> loadGastos()
        }
    }

    private fun loadGastos() {
        viewModelScope.launch {
            getGastoUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                gastos = result.data ?: emptyList(),
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }
}
