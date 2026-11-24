package com.duoc.prerenderrack.repository

import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.remote.RetrofitInstance

class RackRepository {

    private val api = RetrofitInstance.apiRacks

    suspend fun obtenerRacks(): List<Rack> {
        return api.getRacks()
    }

    suspend fun obtenerRackPorId(id: String): Rack {
        return api.getRackById(id)
    }

    suspend fun crearRack(rack: Rack): Rack {
        return api.createRack(rack)
    }

    suspend fun actualizarRack(id: String, rack: Rack): Rack {
        return api.updateRack(id, rack)
    }

    suspend fun eliminarRack(id: String) {
        api.deleteRack(id)
    }
}