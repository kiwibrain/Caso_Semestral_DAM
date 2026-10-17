package com.duoc.prerenderrack.model

import android.net.Uri
import java.time.LocalDateTime

class Rack(
    val id : String,
    var fechaHora : LocalDateTime,
    var responsable : Usuario,
    var estadoAtual : String,
    var descripcion : String,
    val fotoUri : Uri? = null,
    var estadoRevision : String
) {
}