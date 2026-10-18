package com.duoc.prerenderrack.model

sealed class CargoUsuario(val nombre:String) {
    object Supervisor : CargoUsuario("Supervisor")
    object Inspector : CargoUsuario("Inspector")
    object OperadorTecnico : CargoUsuario("Operador Tecnico")
}