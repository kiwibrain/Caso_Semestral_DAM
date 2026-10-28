package com.duoc.prerenderrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun VistaCreacionUsuario(navController: NavController) {
    var nombreCompleto by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val cargo = listOf(
        "Inspector",
        "Especialista Tecnico")
    var cargoSeleccionado by remember {mutableStateOf(0)}
    var modalError by remember { mutableStateOf(false) }
    var mensajeModal by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
            .height(100.dp)
    ) {
        // 1. Box encabezado
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFF3B59C8))
                    .padding(start = 16.dp, top = 35.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Nuevo Usuario",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        //Fin encabezado

        // 2. Nombre completo
        item {
            Text(
                text = "Nombre/Apellidos:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        //Fin text field Nombre completo

        // 3. Rut
        item {
            Text(
                text = "Rut:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = rut,
                onValueChange = {rut = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        //Fin text field Rut

        // 4. Fecha de nacimiento
        item {
            Text(
                text = "Fecha de nacimiento:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = { fechaNacimiento = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        //Fin text field fecha de nacimiento

        // 5. Numero de telefono
        item {
            Text(
                text = "Numero de Telefono:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        } //Fin text field numero de telefono

        // 6. Correo
        item {
            Text(
                text = "Correo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        } // Fin text field Correo

        // 7. Contraseña
        item {
            Text(
                text = "Contraseña:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,bottom = 6.dp)
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
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
            Spacer(modifier = Modifier.height(16.dp))
        } // Fin text field Contraseña

        // 8. Seleccion de cargo
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                cargo.forEachIndexed { index, opcion ->
                    Row(verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                            selected = cargoSeleccionado == index,
                            onClick = {cargoSeleccionado = index}
                        )
                        Text(opcion)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        when{
                            nombreCompleto.isBlank() -> {
                                mensajeModal = "El nombre no debe estar vacio."
                                modalError = true }
                            rut.isBlank() -> {
                                mensajeModal = "El rut no debe estar vacio."
                                modalError = true }
                            fechaNacimiento.isBlank() -> {
                                mensajeModal = "La fecha de nacimiento no debe estar vacia."
                                modalError = true }
                            telefono.isBlank() -> {
                                mensajeModal = "El telefono no debe estar vacio."
                                modalError = true }
                            correo.isBlank() -> {
                                mensajeModal = "El correo no debe estar vacio."
                                modalError = true }
                            contrasena.isBlank() -> {
                                mensajeModal = "La contraseña no debe estar vacia."
                                modalError = true }
                            else -> {
                                navController.popBackStack() }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
                ) {
                    Text(
                        text = "Guardar",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                //Fin boton guardar

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
                ) {
                    Text(
                        text = "Volver",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                //Fin boton volver
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
    if (modalError){
        ModalAlerta(
            titulo = "Error",
            mensaje = mensajeModal,
            onDismiss = { modalError = false }
        )
    }
}
