package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.list

import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoResponseDto

data class GastoListUiState(
    val isLoading: Boolean = false,
    val gastos: List<GastoResponseDto> = emptyList(),
    val error: String? = null
)
