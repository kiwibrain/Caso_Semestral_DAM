package com.duoc.prerenderrack.model

sealed class CargoUsuario {
    object Supervisor : CargoUsuario()
    object Inspector : CargoUsuario()
    object OperadorTecnico : CargoUsuario()
}