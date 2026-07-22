package edu.ucne.jesus_bonilla_ap2_p2.data.gasto.repository

import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.Resource
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.dto.GastoResponseDto
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.dto.GastoRequestDto
import edu.ucne.jesus_bonilla_ap2_p2.data.gasto.remote.remotedatasource.GastoRemoteDataSource
import edu.ucne.jesus_bonilla_ap2_p2.domain.gasto.repository.GastoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val remoteDataSource: GastoRemoteDataSource
) : GastoRepository {
    override fun getGastos(): Flow<Resource<List<GastoResponseDto>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getGastos().onSuccess {
            emit(Resource.Success(it))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getGasto(id: Int): Flow<Resource<GastoResponseDto>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getGasto(id).onSuccess {
            emit(Resource.Success(it))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override suspend fun saveGasto(id: Int, request: GastoRequestDto): Resource<Unit> {
        val result = remoteDataSource.saveGasto(id, request)
        return if (result.isSuccess) {
            Resource.Success(Unit)
        } else {
            Resource.Error(result.exceptionOrNull()?.message ?: "Error al guardar")
        }
    }
}
