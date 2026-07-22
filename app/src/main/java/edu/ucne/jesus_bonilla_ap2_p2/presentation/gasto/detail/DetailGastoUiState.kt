package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.detail

data class DetailGastoUiState(
    val gastoId: Int = 0,
    val fecha: String = "2026-07-22T00:00:00",
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: Double = 0.0,
    val monto: Double = 0.0,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)
