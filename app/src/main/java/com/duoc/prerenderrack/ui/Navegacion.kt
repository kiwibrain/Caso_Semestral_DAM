package com.duoc.prerenderrack.ui

import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun Navegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pantalla_inicial"){
        composable("pantalla_inicial") { PantallaInicial(navController)}
        //composable("pantalla_login") { PantallaLogin(navController)}
        //agregar mas pantallas
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