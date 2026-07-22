package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.list

sealed interface GastoListUiEvent {
    data object Refresh : GastoListUiEvent
}
