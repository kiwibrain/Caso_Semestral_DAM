package com.duoc.prerenderrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.viewmodel.UsuarioViewModel

@Composable
fun PantallaLogin(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val cargando by usuarioViewModel.cargando.collectAsState()
    val mensajeErrorVm by usuarioViewModel.mensajeError.collectAsState()

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorRol by remember { mutableStateOf<String?>(null) }

    val errorActual = mensajeErrorVm ?: errorRol

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
    ) {
        // Box encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF3B59C8))
                .padding(start = 16.dp, top = 35.dp, end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Iniciar Sesión",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Formulario
        Column {
            // CORREO
            Text(
                text = "Correo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // CONTRASEÑA
            Text(
                text = "Contraseña:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Ingresar conectado al ViewModel
        BotonCargando(
            texto = "Ingresar",
            cargando = cargando,
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
            onClick = {
                usuarioViewModel.iniciarSesion(correo, contrasena) { usuarioEncontrado ->
                    when (usuarioEncontrado.cargo) {
                        "Supervisor" -> {
                            navController.navigate("menu-supervisor") {
                                popUpTo("pantalla_login") { inclusive = true }
                            }
                        }
                        "Inspector", "OperadorTecnico", "Especialista Tecnico" -> {
                            navController.navigate("menu-inspector") {
                                popUpTo("pantalla_login") { inclusive = true }
                            }
                        }
                        else -> {
                            errorRol = "Rol '${usuarioEncontrado.cargo}' no reconocido."
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("pantalla_inicial") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Volver",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (errorActual != null) {
        ModalAlerta(
            titulo = "Error de inicio de sesión",
            mensaje = errorActual,
            onDismiss = {
                usuarioViewModel.limpiarError()
                errorRol = null
            }
        )
    }
}