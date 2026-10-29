package com.duoc.prerenderrack.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.prerenderrack.repository.UsuarioRepository

class UsuarioViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    // Lógica de autenticación y datos de usuario usando usuarioRepository
}