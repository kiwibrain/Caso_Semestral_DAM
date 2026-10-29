package com.duoc.prerenderrack.model

class Rack(
    val id: String,
    var fechaHora: String, // Cambiado de LocalDateTime a String
    var responsable: Usuario,
    var estadoAtual: String,
    var descripcion: String,
    val fotoUri: String? = null, // Cambiado de Uri a String para compatibilidad con Gson
    var estadoRevision: String
)