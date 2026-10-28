package com.duoc.prerenderrack.ui

import android.content.Context // <-- Importante
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
import androidx.compose.ui.platform.LocalContext // <-- Importante
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun PantallaLogin(navController: NavController){
    val context = LocalContext.current // <-- Obtener contexto
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE) // <-- Instanciar preferencias

    var modalError by remember { mutableStateOf(false) }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Box encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF3B59C8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Iniciar Sesión",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(12.dp))
        //Formulario
        Column{
            Text("Correo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text("Contraseña:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
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

        Button(
            onClick = {
                if (correo == "inspector@duoc.cl" && contrasena == "1234"){
                    // GUARDAR SESIÓN LOCALMENTE
                    sharedPreferences.edit().putString("logged_user", correo).apply()

                    navController.navigate("menu-inspector") {
                        popUpTo("pantalla_login") { inclusive = true }
                    }
                } else if (correo == "supervisor@duoc.cl" && contrasena == "4321"){
                    // GUARDAR SESIÓN LOCALMENTE
                    sharedPreferences.edit().putString("logged_user", correo).apply()

                    navController.navigate("menu-supervisor") {
                        popUpTo("pantalla_login") { inclusive = true }
                    }
                } else {
                    modalError = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Ingresar", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("pantalla_inicial") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
        ) {
            Text(
                text = "Volver",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (modalError){
        ModalAlerta(
            titulo = "Error",
            mensaje = "Credenciales incorrectas",
            onDismiss = { modalError = false }
        )
    }
}