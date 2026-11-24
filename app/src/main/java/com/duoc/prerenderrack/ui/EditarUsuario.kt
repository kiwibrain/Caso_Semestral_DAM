package com.duoc.prerenderrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.duoc.prerenderrack.viewmodel.UsuarioViewModel

@Composable
fun EditarUsuario(
    navController: NavController,
    usuarioId: String,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val usuarios by usuarioViewModel.usuarios.collectAsState()
    val cargando by usuarioViewModel.cargando.collectAsState()
    val mensajeErrorVm by usuarioViewModel.mensajeError.collectAsState()

    val usuarioActual = remember(usuarios, usuarioId) {
        usuarios.find { it.id?.toString() == usuarioId }
    }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var cargo by remember { mutableStateOf("Inspector") }
    var errorValidacion by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        if (usuarios.isEmpty()) {
            usuarioViewModel.cargarUsuarios()
        }
    }

    LaunchedEffect(usuarioActual) {
        usuarioActual?.let {
            nombre = it.nombre
            correo = it.correo
            cargo = it.cargo
        }
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
                    text = "Editar Usuario",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Formulario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (usuarioActual == null) {
                CircularProgressIndicator(color = Color(0xFF1C2C6B))
            } else {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cargo",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                val cargosDisponibles = listOf("Inspector", "Especialista Tecnico", "Supervisor")
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    cargosDisponibles.forEach { opcion ->
                        Button(
                            onClick = { cargo = opcion },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (cargo.equals(opcion, ignoreCase = true)) {
                                    Color(0xFF1C2C6B)
                                } else {
                                    Color(0xFF679CF6)
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(opcion, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                errorValidacion?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it, color = Color(0xFFB71C1C), fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    enabled = !cargando,
                    onClick = {
                        if (nombre.isBlank() || correo.isBlank()) {
                            errorValidacion = "Nombre y correo no pueden estar vacíos"
                        } else {
                            errorValidacion = null
                            val usuarioActualizado = usuarioActual.copy(
                                nombre = nombre.trim(),
                                correo = correo.trim(),
                                cargo = cargo
                            )
                            usuarioViewModel.actualizarUsuario(
                                id = usuarioId,
                                usuario = usuarioActualizado,
                                onExito = { navController.popBackStack() }
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(
                        text = if (cargando) "GUARDANDO..." else "GUARDAR CAMBIOS",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = { navController.popBackStack() }) {
                    Text(
                        text = "Cancelar",
                        color = Color(0xFF1C2C6B),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    if (mensajeErrorVm != null) {
        ModalAlerta(
            titulo = "Error al actualizar",
            mensaje = mensajeErrorVm!!,
            onDismiss = { usuarioViewModel.limpiarError() }
        )
    }
}