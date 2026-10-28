package com.duoc.prerenderrack.ui

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.duoc.prerenderrack.R

@Composable
fun MenuInspector(navController: NavController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    //columna encabezado
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
            .height(100.dp)
    ) {
        //box encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF3B59C8))
                .padding(start = 16.dp, top = 35.dp, end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //imagen usuario
                Image(
                    painter = painterResource(id = R.drawable.juanin),
                    contentDescription = "icono inspector",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                //nombre usuario y cargo
                Column{
                    Text(
                        text = "Juanin Juan Harry",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Inspector",
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )
                }
            }
        }//Fin box encabezado

        Spacer(modifier = Modifier.height(16.dp))

        //columna elementos de vista
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF88BBFF))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = "Sistema de Registro Racks",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            //Box boton ingresar registro
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("nuevo_registro")
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
                onClick = {
                    // BORRAR SESIÓN AL SALIR
                    sharedPreferences.edit().clear().apply()
                    navController.navigate("pantalla_login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
            ) {
                Text(
                    text = "Volver",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }//Fin boton volver

        }//fin column contenido

    }//fin column general

}//fin menu inspector