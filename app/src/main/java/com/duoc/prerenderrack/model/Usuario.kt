package com.duoc.prerenderrack.model

import java.time.LocalDate

class Usuario(
    val rut : String,
    var nombre : String,
    val fechaNacimiento : LocalDate,
    var numeroTelefono : String,
    var correo : String,
    var contrasena : String,
    var cargo : CargoUsuario
) {
}