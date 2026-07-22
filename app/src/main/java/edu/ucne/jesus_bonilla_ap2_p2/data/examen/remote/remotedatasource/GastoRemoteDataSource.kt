package edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.remotedatasource

import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.GastosApi
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoResponseDto
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoRequestDto
import retrofit2.HttpException
import javax.inject.Inject

class GastoRemoteDataSource @Inject constructor(
    private val api: GastosApi
) {
    suspend fun getGastos(): Result<List<GastoResponseDto>> {
        return try {
            val response = api.getGastos()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getGasto(id: Int): Result<GastoResponseDto> {
        return try {
            val response = api.getGasto(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun saveGasto(id: Int, request: GastoRequestDto): Result<Unit> {
        return try {
            if (id == 0) {
                val response = api.postGasto(request)
                if (response.isSuccessful) Result.success(Unit)
                else Result.failure(Exception("Error al crear ${response.code()}"))
            } else {
                val response = api.putGasto(id, request)
                if (response.isSuccessful) Result.success(Unit)
                else Result.failure(Exception("Error al actualizar ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

}