package com.duoc.prerenderrack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.repository.RackRepository
import com.duoc.prerenderrack.repository.UsuarioRepository
import kotlinx.coroutines.launch // Importación necesaria para las corrutinas

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
        // Cargar usuario activo de forma normal (local)
        usuarioActual = usuarioRepository.obtenerUsuarioLogueado()

        // Cargar la lista de racks desde internet (asíncrono)
        viewModelScope.launch {
            try {
                listaRacks = rackRepository.getRacks()
            } catch (e: Exception) {
                // Si hay un error de conexión, evitamos que la app se caiga
                e.printStackTrace()
            }
        }
    }

    fun cerrarSesion(onSesionCerrada: () -> Unit) {
        usuarioRepository.cerrarSesion()
        onSesionCerrada()
    }
}