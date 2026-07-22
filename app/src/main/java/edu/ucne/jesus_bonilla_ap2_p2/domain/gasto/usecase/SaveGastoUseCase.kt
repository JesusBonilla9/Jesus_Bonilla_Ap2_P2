package edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.usecase

import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoRequestDto
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.repository.GastoRepository
import javax.inject.Inject

class SaveGastoUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke(id: Int, request: GastoRequestDto): Resource<Unit> {
        return repository.saveGasto(id, request)
    }
}
