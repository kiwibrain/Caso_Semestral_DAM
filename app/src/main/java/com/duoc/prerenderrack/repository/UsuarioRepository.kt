package com.duoc.prerenderrack.repository

import android.content.Context
import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.remote.RetrofitInstance
import com.duoc.prerenderrack.remote.UsuarioApiService
import com.google.gson.Gson

class UsuarioRepository(
    context: Context? = null,
    private val api: UsuarioApiService = RetrofitInstance.apiUsuarios

) {

    private val prefs = context?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    suspend fun obtenerUsuarios(): List<Usuario> {
        return api.getUsuarios()
    }

    suspend fun obtenerUsuarioPorId(id: String): Usuario {
        return api.getUsuarioById(id)
    }

    suspend fun crearUsuario(usuario: Usuario): Usuario {
        return api.createUsuario(usuario)
    }

    suspend fun actualizarUsuario(id: String, usuario: Usuario): Usuario {
        return api.updateUsuario(id, usuario)
    }

    suspend fun eliminarUsuario(id: String) {
        api.deleteUsuario(id)
    }

    // PERSISTENCIA
    fun guardarSesion(usuario: Usuario) {
        val json = gson.toJson(usuario)
        prefs?.edit()
            ?.putString("logged_user", usuario.correo)
            ?.putString("USER_${usuario.correo}", json)
            ?.apply()
    }

    fun obtenerUsuarioLogueado(): Usuario? {
        val correo = prefs?.getString("logged_user", null) ?: return null
        val jsonUsuario = prefs?.getString("USER_$correo", null)

        return if (jsonUsuario != null) {
            gson.fromJson(jsonUsuario, Usuario::class.java)
        } else {
            null
        }
    }

    fun cerrarSesion() {
        prefs?.edit()?.remove("logged_user")?.apply()
    }
}