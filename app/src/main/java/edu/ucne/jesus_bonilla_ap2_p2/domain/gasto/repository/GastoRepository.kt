package edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.repository

import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoResponseDto
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoRequestDto
import kotlinx.coroutines.flow.Flow

interface GastoRepository {
    fun getGastos(): Flow<Resource<List<GastoResponseDto>>>
    fun getGasto(id: Int): Flow<Resource<GastoResponseDto>>
    suspend fun saveGasto(id: Int, request: GastoRequestDto): Resource<Unit>
}
