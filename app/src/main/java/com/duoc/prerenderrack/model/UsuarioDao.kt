package com.duoc.prerenderrack.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {

    @Insert
    suspend fun crearUsuario(usuario: Usuario)

    @Update
    suspend fun actualizarUsuario(usuario: Usuario)

    @Delete
    suspend fun eliminarUsuario(usuario: Usuario)

    // Validar inicio de sesión por correo y contraseña
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    suspend fun login(correo: String, contrasena: String): Usuario?

    // Obtener la lista completa de usuarios
    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodosLosUsuarios(): List<Usuario>
}