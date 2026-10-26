package com.duoc.prerenderrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey
    val rut: String,
    var nombre: String,
    val fechaNacimiento: String,
    var numeroTelefono: String,
    var correo: String,
    var contrasena: String,
    var cargo: String
)