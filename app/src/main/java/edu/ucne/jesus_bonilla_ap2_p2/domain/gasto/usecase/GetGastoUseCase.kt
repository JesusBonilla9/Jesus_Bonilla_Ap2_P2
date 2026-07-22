package edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.usecase

import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.dto.GastoResponseDto
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGastoUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    operator fun invoke(): Flow<Resource<List<GastoResponseDto>>> {
        return repository.getGastos()
    }
}
