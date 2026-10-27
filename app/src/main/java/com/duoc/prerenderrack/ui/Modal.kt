package com.duoc.prerenderrack.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ModalAlerta(
    titulo: String,
    mensaje: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = titulo, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = mensaje)
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
            ) {
                Text("Aceptar", color = Color.White)
            }
        }
    )
}