package com.duoc.prerenderrack.repository

import android.content.Context
import com.duoc.prerenderrack.model.Usuario
import com.google.gson.Gson

class UsuarioRepository(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Obtiene los datos del usuario que inició sesión actualmente
    fun obtenerUsuarioLogueado(): Usuario? {
        val correo = prefs.getString("logged_user", null) ?: return null
        val jsonUsuario = prefs.getString("USER_$correo", null)

        return if (jsonUsuario != null) {
            gson.fromJson(jsonUsuario, Usuario::class.java)
        } else {
            // Usuario por defecto en caso de no estar registrado aún en el JSON
            Usuario(
                rut = "12345678-9",
                nombre = "Juanin Juan Harry",
                fechaNacimiento = "01/01/1990",
                numeroTelefono = "912345678",
                correo = correo,
                contrasena = "1234",
                cargo = "Inspector"
            )
        }
    }

    // Guardar o actualizar un usuario
    fun guardarUsuario(usuario: Usuario) {
        val json = gson.toJson(usuario)
        prefs.edit().putString("USER_${usuario.correo}", json).apply()
    }

    // Borrar la sesión activa
    fun cerrarSesion() {
        prefs.edit().remove("logged_user").apply()
    }
}