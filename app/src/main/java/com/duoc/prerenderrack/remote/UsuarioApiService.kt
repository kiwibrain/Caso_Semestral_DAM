package com.duoc.prerenderrack.remote

import com.duoc.prerenderrack.model.Usuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApiService {
    @GET("usuarios")
    suspend fun getUsuarios(): List<Usuario> // Debes tener un data class Usuario

    @GET("usuarios/{id}")
    suspend fun getUsuarioById(@Path("id") id: String): Usuario

    @POST("usuarios")
    suspend fun createUsuario(@Body usuario: Usuario): Usuario

    @PATCH("usuarios/{id}")
    suspend fun updateUsuario(@Path("id") id: String, @Body usuario: Usuario): Usuario

    @DELETE("usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") id: String)
}