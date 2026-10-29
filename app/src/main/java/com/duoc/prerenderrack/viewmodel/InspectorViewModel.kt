package com.duoc.prerenderrack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.repository.RackRepository
import com.duoc.prerenderrack.repository.UsuarioRepository

class InspectorViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val rackRepository: RackRepository
) : ViewModel() {

    // Estado del usuario logueado
    var usuarioActual by mutableStateOf<Usuario?>(null)
        private set

    // Estado de la lista de logs/racks
    var listaRacks by mutableStateOf<List<Rack>>(emptyList())
        private set

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        // Cargar usuario activo y la lista de registros desde los repositorios
        usuarioActual = usuarioRepository.obtenerUsuarioLogueado()
        listaRacks = rackRepository.obtenerTodosLosRacks()
    }

    fun cerrarSesion(onSesionCerrada: () -> Unit) {
        usuarioRepository.cerrarSesion()
        onSesionCerrada()
    }
}