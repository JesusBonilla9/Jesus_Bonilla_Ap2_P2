package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.dto.GastoRequestDto
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.usecase.GetGastoDetailUseCase
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.usecase.SaveGastoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailGastoViewModel @Inject constructor(
    private val getGastoDetailUseCase: GetGastoDetailUseCase,
    private val saveGastoUseCase: SaveGastoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailGastoUiState())
    val uiState = _state.asStateFlow()

    fun onFechaChange(fecha: String) {
        _state.update { it.copy(fecha = fecha) }
    }

    fun onSuplidorChange(suplidor: String) {
        _state.update { it.copy(suplidor = suplidor) }
    }

    fun onNcfChange(ncf: String) {
        _state.update { it.copy(ncf = ncf) }
    }

    fun onItbisChange(itbis: String) {
        val value = itbis.toDoubleOrNull() ?: 0.0
        _state.update { it.copy(itbis = value) }
    }

    fun onMontoChange(monto: String) {
        val value = monto.toDoubleOrNull() ?: 0.0
        _state.update { it.copy(monto = value) }
    }

    fun resetState() {
        _state.value = DetailGastoUiState()
    }

    fun getGasto(id: Int) {
        viewModelScope.launch {
            getGastoDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        val gasto = result.data
                        if (gasto != null) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    gastoId = gasto.gastoId,
                                    fecha = gasto.fecha,
                                    suplidor = gasto.suplidor,
                                    ncf = gasto.ncf ?: "",
                                    itbis = gasto.itbis,
                                    monto = gasto.monto
                                )
                            }
                        }
                    }
                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun saveGasto() {
        if (_state.value.suplidor.isBlank()) {
            _state.update { it.copy(error = "El suplidor no puede estar vacío") }
            return
        }
        if (_state.value.monto <= 0) {
            _state.update { it.copy(error = "El monto debe ser mayor a 0") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val request = GastoRequestDto(
                gastoId = _state.value.gastoId,
                fecha = _state.value.fecha,
                suplidor = _state.value.suplidor,
                ncf = _state.value.ncf,
                itbis = _state.value.itbis,
                monto = _state.value.monto
            )
            when (val result = saveGastoUseCase(_state.value.gastoId, request)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isSaved = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
