package edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto

data class GastoResponseDto(
    val gastoId: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String?,
    val itbis: Double,
    val monto: Double
)
