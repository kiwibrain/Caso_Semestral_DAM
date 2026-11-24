package com.duoc.prerenderrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsuarioRepository(application)

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError: StateFlow<String?> = _mensajeError

    // --- LOGIN Y SESIÓN ---

    fun iniciarSesion(
        correo: String,
        contrasena: String,
        onExito: (Usuario) -> Unit
    ) {
        if (correo.isBlank() || contrasena.isBlank()) {
            _mensajeError.value = "Por favor, ingresa correo y contraseña."
            return
        }

        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                val lista = repository.obtenerUsuarios()
                val encontrado = lista.find {
                    it.correo.trim().equals(correo.trim(), ignoreCase = true)
                }

                when {
                    encontrado == null -> {
                        _mensajeError.value = "El correo ingresado no existe en nuestros registros."
                    }
                    encontrado.contrasena != contrasena -> {
                        _mensajeError.value = "Contraseña incorrecta."
                    }
                    else -> {
                        repository.guardarSesion(encontrado)
                        onExito(encontrado)
                    }
                }
            } catch (e: Exception) {
                _mensajeError.value = "Error de conexión con el servidor."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun obtenerUsuarioLogueado(): Usuario? = repository.obtenerUsuarioLogueado()

    fun cerrarSesion() {
        repository.cerrarSesion()
    }

    // --- CRUD DE USUARIOS ---

    fun cargarUsuarios() {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                _usuarios.value = repository.obtenerUsuarios()
            } catch (e: Exception) {
                _mensajeError.value = "Error al cargar la lista de usuarios."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun crearUsuario(usuario: Usuario, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.crearUsuario(usuario)
                cargarUsuarios() // Refresca la lista automáticamente
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al crear el usuario."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun actualizarUsuario(id: String, usuario: Usuario, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.actualizarUsuario(id, usuario)
                cargarUsuarios()
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al actualizar el usuario."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun eliminarUsuario(id: String, onExito: () -> Unit = {}) {
        viewModelScope.launch {
            _cargando.value = true
            _mensajeError.value = null
            try {
                repository.eliminarUsuario(id)
                cargarUsuarios()
                onExito()
            } catch (e: Exception) {
                _mensajeError.value = "Error al eliminar el usuario."
            } finally {
                _cargando.value = false
            }
        }
    }

    fun limpiarError() {
        _mensajeError.value = null
    }
}