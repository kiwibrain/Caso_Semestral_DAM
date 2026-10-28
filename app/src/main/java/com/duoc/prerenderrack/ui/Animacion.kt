package com.duoc.prerenderrack.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BotonCargando(
    texto: String,
    cargando: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        enabled = !cargando,
        modifier = modifier
        ) {
            if (cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            } else {
                Text(texto)
            }
    }
}