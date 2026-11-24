package com.duoc.prerenderrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.viewmodel.UsuarioViewModel

@Composable
fun ListaUsuarios(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val usuarios by usuarioViewModel.usuarios.collectAsState()
    val cargando by usuarioViewModel.cargando.collectAsState()
    val mensajeError by usuarioViewModel.mensajeError.collectAsState()
    var usuarioAEliminar by remember { mutableStateOf<Usuario?>(null) }

    LaunchedEffect(Unit) {
        usuarioViewModel.cargarUsuarios()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
    ) {
        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFF3B59C8))
                .padding(start = 8.dp, end = 16.dp, top = 35.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lista de Usuarios",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when {
                cargando && usuarios.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF1C2C6B))
                    }
                }
                usuarios.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay usuarios registrados",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(usuarios) { usuario ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF679CF6))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = usuario.nombre,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Cargo: ${usuario.cargo}",
                                            color = Color(0xFFE3ECFF),
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = usuario.correo,
                                            color = Color(0xFFE3ECFF),
                                            fontSize = 14.sp
                                        )
                                    }

                                    Row {
                                        IconButton(
                                            onClick = {
                                                val id = usuario.id?.toString()
                                                if (!id.isNullOrBlank()) {
                                                    navController.navigate("editar_usuario/$id")
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Editar usuario",
                                                tint = Color(0xFF1C2C6B)
                                            )
                                        }

                                        IconButton(
                                            onClick = { usuarioAEliminar = usuario }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Eliminar usuario",
                                                tint = Color(0xFFB71C1C)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Confirmación de eliminación
    usuarioAEliminar?.let { usuario ->
        AlertDialog(
            onDismissRequest = { usuarioAEliminar = null },
            title = { Text("Eliminar usuario") },
            text = { Text("¿Seguro que deseas eliminar a ${usuario.nombre}? Esta acción no se puede deshacer.") },
            confirmButton = {
                Button(
                    onClick = {
                        val id = usuario.id?.toString()
                        if (!id.isNullOrBlank()) {
                            usuarioViewModel.eliminarUsuario(id)
                        }
                        usuarioAEliminar = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
                ) {
                    Text("Eliminar", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { usuarioAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (mensajeError != null) {
        ModalAlerta(
            titulo = "Error",
            mensaje = mensajeError!!,
            onDismiss = { usuarioViewModel.limpiarError() }
        )
    }
}