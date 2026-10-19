package com.duoc.prerenderrack.ui

import android.text.style.BackgroundColorSpan
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.prerenderrack.R
import com.duoc.prerenderrack.model.CargoUsuario

@Composable
fun Navegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pantalla_inicial"){
        composable("pantalla_inicial") { PantallaInicial(navController)}
        composable("pantalla_login") { PantallaLogin(navController)}
        composable("menu-inspector") {MenuInspector(navController)}
        //agregar mas pantallas mas tarde
    }
}

@Composable
fun PantallaInicial(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B52B3)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.cmc360_logo),
                contentDescription = "Logo cmc360",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }//fin box imagen

        //Box boton iniciar sesion
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {navController.navigate("pantalla_login")},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                Text(
                    text = "Iniciar Sesión",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }//fin box Boton iniciar sesion

    }//fin column

}//fin fun pantalla inicial

@Composable
fun PantallaLogin(navController: NavController){

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val opcionesCargo = listOf(
        CargoUsuario.Supervisor,
        CargoUsuario.Inspector,
        CargoUsuario.OperadorTecnico
    )
    var cargoSeleccionado by remember { mutableStateOf<CargoUsuario>(CargoUsuario.Supervisor) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
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
            )//Fin text
        }//Fin box encabezado

        //Formulario
        Column() {
            //correo
            Text("Correo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )//Fin text
            OutlinedTextField(
                value = correo,
                onValueChange = {correo = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )//fin colors

            )//Fin text field correo

            Spacer(modifier = Modifier.height(14.dp))

            //contraseña
            Text("Contraseña:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )//Fin text
            OutlinedTextField(
                value = contrasena,
                onValueChange = {contrasena = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )//fin colors

            )//Fin text field contraseña

            //cargo
            Text("Cargo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )//Fin text

            //opciones de cargo con radioButton
            opcionesCargo.forEach { cargoUsuario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = cargoSeleccionado == cargoUsuario,
                        onClick = {cargoSeleccionado = cargoUsuario}
                    )//Fin RadioButton
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = cargoUsuario.nombre,
                        color = Color(0xFF1E2749),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }//Fin row

            }//fin opciones cargos

        }//Fin formulario

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("menu-inspector")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Ingresar", color = Color.White)
        }//Fin Button

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {navController.navigate("pantalla_inicial")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
        ) {
            Text(
            text = "Volver",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
            )
        }//Fin boton volver

    }//Fin Column

}//Fin PantallaLogin


@Composable
fun MenuInspector(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
    ) {
        //Box encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFF3B59C8)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "nombre del inspector",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )//Fin text
        }//Fin box encabezado

        Spacer(modifier = Modifier.height(16.dp))

        Row() {
            Text(
                text = "Sistema de Registro Racks",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        //Box boton ingresar registro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    //enlace a creacion de nuevo registro
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            ) {
                Text(
                    text = "NUEVO REGISTRO",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }//fin box Boton ingresar registro
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {navController.navigate("pantalla_login")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
        ) {
            Text(
                text = "Volver",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }//Fin boton volver

    }//fin column

}//fin menu inspector