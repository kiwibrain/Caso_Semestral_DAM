package com.duoc.prerenderrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.prerenderrack.repository.RackRepository
import com.duoc.prerenderrack.repository.UsuarioRepository
import com.duoc.prerenderrack.ui.Navegacion
import com.duoc.prerenderrack.ui.theme.PrerenderRackTheme
import com.duoc.prerenderrack.viewmodel.InspectorViewModel

class MainActivity : ComponentActivity() {

    private val usuarioRepository by lazy { UsuarioRepository(applicationContext) }
    private val rackRepository by lazy { RackRepository(applicationContext) }

    private val inspectorViewModel by lazy {
        InspectorViewModel(usuarioRepository, rackRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Pasas el ViewModel a tu navegación
            Navegacion()
        }
    }
}



