package com.duoc.prerenderrack.remote

import com.duoc.prerenderrack.model.Rack
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface RackApiService {
    @GET("racks")
    suspend fun getRacks(): List<Rack>

    @GET("racks/{id}")
    suspend fun getRackById(@Path("id") id: String): Rack

    @POST("racks")
    suspend fun createRack(@Body rack: Rack): Rack // Asume que Rack tiene los campos necesarios

    @PATCH("racks/{id}")
    suspend fun updateRack(@Path("id") id: String, @Body rack: Rack): Rack

    @DELETE("racks/{id}")
    suspend fun deleteRack(@Path("id") id: String)
}