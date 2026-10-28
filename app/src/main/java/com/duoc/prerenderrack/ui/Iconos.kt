package com.duoc.prerenderrack.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Iconos(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Enviar",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Texto")
        }
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Enviar",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Texto")
        }
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Enviar",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Texto")
        }
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Enviar",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Texto")
        }


        /*
        • Icons.Default.Send (El que estás usando para enviar)
        • Icons.Default.ArrowBack (Flecha para volver atrás)
        • Icons.Default.Favorite (Corazón)
        • Icons.Default.Share (Icono de compartir)
        • Icons.Default.Check (Un ticket/visto bueno)
        • Icons.Default.Close (Una X para cerrar)
        • Icons.Default.Menu (Las tres líneas de menú)
        • Icons.Default.Search (Lupa de búsqueda)
        • Icons.Default.Settings (Engranaje de ajustes)
        • Icons.Default.Home (Casa de inicio)
        */
    }
}