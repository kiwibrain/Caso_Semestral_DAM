package com.duoc.prerenderrack.remote

import com.duoc.prerenderrack.model.Rack
import retrofit2.http.GET

interface ApiService {
    @GET("racks")
    suspend fun getRacks(): List<Rack>
}