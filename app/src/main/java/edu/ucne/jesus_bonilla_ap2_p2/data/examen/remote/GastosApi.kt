package edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote

import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoResponseDto
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.dto.GastoRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GastosApi{
    @GET("api/Gastos")
    suspend fun getGastos(): Response<List<GastoResponseDto>>

    @GET("api/Gastos/{id}")
    suspend fun getGasto(@Path("id") id: Int): Response<GastoResponseDto>

    @POST("api/Gastos")
    suspend fun postGasto(@Body gasto: GastoRequestDto): Response<GastoResponseDto>

    @PUT("api/Gastos/{id}")
    suspend fun putGasto(@Path("id") id: Int, @Body gasto: GastoRequestDto): Response<Unit>
}