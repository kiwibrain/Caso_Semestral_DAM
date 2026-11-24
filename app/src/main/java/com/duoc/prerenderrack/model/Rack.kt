package com.duoc.prerenderrack.model

data class Rack(
    val id: String,
    var fecha_hora: String,
    var modelo : String,
    var responsable: String,
    var estadoActual: String,
    var descripcion: String,
    val fotoUri: String? = null, // Cambiado de Uri a String para compatibilidad con Gson
    var estadoRevision: String
)