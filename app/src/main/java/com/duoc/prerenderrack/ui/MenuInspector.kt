package com.duoc.prerenderrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.R
import com.duoc.prerenderrack.viewmodel.RackViewModel
import com.duoc.prerenderrack.viewmodel.UsuarioViewModel

@Composable
fun MenuInspector(
    navController: NavController,
    rackViewModel: RackViewModel = viewModel(),
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val usuario = remember { usuarioViewModel.obtenerUsuarioLogueado() }
    val listaRacks by rackViewModel.racks.collectAsState()

    // Carga los racks desde Xano al abrir la pantalla
    LaunchedEffect(Unit) {
        rackViewModel.cargarRacks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
    ) {
        // Box Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF3B59C8))
                .padding(start = 16.dp, top = 35.dp, end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.juanin),
                    contentDescription = "icono inspector",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = usuario?.nombre ?: "Inspector",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = usuario?.cargo ?: "Inspector",
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF88BBFF))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sistema de Registro Racks",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botón Nuevo Registro
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigate("nuevo_registro") },
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
            }

            // Botón Cerrar Sesión mediante el ViewModel
            Button(
                onClick = {
                    usuarioViewModel.cerrarSesion()
                    navController.navigate("pantalla_login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Cerrar sesión",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cerrar Sesión",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 24.dp),
                thickness = 2.dp,
                color = Color(0xFF3B59C8)
            )

            // Lista de Logs Dinámica
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ÚLTIMOS LOGS REGISTRADOS",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF679CF6)),
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(listaRacks) { rack ->
                            ItemLog(
                                fecha = rack.fecha_hora,
                                rack = "N° ${rack.id}",
                                descripcion = rack.descripcion
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { navController.navigate("menu-racks") },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B59C8)),
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Ver más", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}