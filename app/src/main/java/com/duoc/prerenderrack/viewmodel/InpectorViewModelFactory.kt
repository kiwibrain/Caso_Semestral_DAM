// ViewModelFactory.kt
package com.duoc.prerenderrack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duoc.prerenderrack.repository.RackRepository
import com.duoc.prerenderrack.repository.UsuarioRepository

class InspectorViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val usuarioRepo = UsuarioRepository(context)
        val rackRepo = RackRepository(context)
        return InspectorViewModel(usuarioRepo, rackRepo) as T
    }
}

class UsuarioViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val usuarioRepo = UsuarioRepository(context)
        return UsuarioViewModel(usuarioRepo) as T
    }
}