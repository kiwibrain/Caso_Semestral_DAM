package com.duoc.prerenderrack.repository

import android.content.Context
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.model.Usuario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RackRepository(context: Context) {

    private val prefs = context.getSharedPreferences("rack_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun obtenerTodosLosRacks(): List<Rack> {
        val json = prefs.getString("lista_racks", null) ?: return obtenerRacksIniciales()
        val type = object : TypeToken<List<Rack>>() {}.type
        val lista: List<Rack>? = gson.fromJson(json, type)
        return if (lista.isNullOrEmpty()) obtenerRacksIniciales() else lista
    }

    fun guardarRack(rack: Rack) {
        val listaActual = obtenerTodosLosRacks().toMutableList()
        listaActual.add(0, rack)
        val json = gson.toJson(listaActual)
        prefs.edit().putString("lista_racks", json).apply()
    }

    private fun obtenerRacksIniciales(): List<Rack> {
        val usuarioEjemplo = Usuario(
            rut = "12345678-9",
            nombre = "Juanin Juan Harry",
            fechaNacimiento = "01/01/1990",
            numeroTelefono = "912345678",
            correo = "inspector@duoc.cl",
            contrasena = "1234",
            cargo = "Inspector"
        )

        return listOf(
            Rack(
                id = "420",
                fechaHora = "25-03-2026 - 12:01 hrs",
                responsable = usuarioEjemplo,
                estadoAtual = "Operativo",
                descripcion = "Este rack esta pulento",
                estadoRevision = "Aprobado"
            ),
            Rack(
                id = "67",
                fechaHora = "02-04-2026 - 09:35 hrs",
                responsable = usuarioEjemplo,
                estadoAtual = "En Revisión",
                descripcion = "pipipipi",
                estadoRevision = "Pendiente"
            ),
            Rack(
                id = "69",
                fechaHora = "22-05-2026 - 15:55 hrs",
                responsable = usuarioEjemplo,
                estadoAtual = "Operativo",
                descripcion = "very nice",
                estadoRevision = "Aprobado"
            ),
            Rack(
                id = "666",
                fechaHora = "26-05-2026 - 08:23 hrs",
                responsable = usuarioEjemplo,
                estadoAtual = "Critico",
                descripcion = "que buen espectaculo",
                estadoRevision = "Rechazado"
            )
        )
    }
}