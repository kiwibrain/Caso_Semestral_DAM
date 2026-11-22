package com.duoc.prerenderrack.repository

import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.remote.RetrofitInstance // Asegúrate de tener configurada esta clase en tu proyecto

class RackRepository {

    // Función suspendida para obtener los datos desde la API/Base de datos
    suspend fun getRacks(): List<Rack> {
        return RetrofitInstance.api.getRacks()
    }

    // Si tu API también tiene un endpoint para guardar (POST), se adaptaría de esta forma:
    /*
    suspend fun guardarRack(rack: Rack) {
        RetrofitInstance.api.createRack(rack)
    }
    */
}