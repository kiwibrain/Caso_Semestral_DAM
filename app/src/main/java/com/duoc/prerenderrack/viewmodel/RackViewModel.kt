package com.duoc.prerenderrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.repository.RackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RackViewModel : ViewModel() {
    private val repository = RackRepository()

    private val _racks = MutableStateFlow<List<Rack>>(emptyList())
    val racks: StateFlow<List<Rack>> = _racks

    private val _rackSeleccionado = MutableStateFlow<Rack?>(null)
    val rackSeleccionado: StateFlow<Rack?> = _rackSeleccionado

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError: StateFlow<String?> = _mensajeError

    fun cargarRacks() {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                _racks.value = repository.obtenerRacks()
            } catch (e: Exception) {
                _mensajeError.value = "Error al cargar los racks."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun cargarRackPorId(id: String) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                _rackSeleccionado.value = repository.obtenerRackPorId(id)
            } catch (e: Exception) {
                _mensajeError.value = "Error al cargar el detalle del rack."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun crearRack(rack: Rack, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.crearRack(rack)
                cargarRacks()
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al crear el rack."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun actualizarRack(id: String, rack: Rack, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.actualizarRack(id, rack)
                cargarRacks()
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al actualizar el rack."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun eliminarRack(id: String, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.eliminarRack(id)
                cargarRacks()
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al eliminar el rack."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun limpiarError() {
        _mensajeError.value = null
    }
}