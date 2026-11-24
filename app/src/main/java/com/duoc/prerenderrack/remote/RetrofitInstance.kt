package com.duoc.prerenderrack.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Api de racks
    val apiRacks: RackApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://x8ki-letl-twmt.n7.xano.io/api:s-qpX8dR/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RackApiService::class.java)
    }

    // Api de usuarios
    val apiUsuarios: UsuarioApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://x8ki-letl-twmt.n7.xano.io/api:xILd-V6_/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApiService::class.java)
    }
}