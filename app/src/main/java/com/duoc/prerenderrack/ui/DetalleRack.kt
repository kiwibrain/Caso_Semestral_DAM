package com.duoc.prerenderrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.R
import com.duoc.prerenderrack.viewmodel.RackViewModel

@Composable
fun DetalleRack(
    navController: NavController,
    rackId: String,
    rackViewModel: RackViewModel = viewModel()
) {
    val racks by rackViewModel.racks.collectAsState()
    val rack = racks.find { it.id == rackId }

    val colorFondo = Color(0xFF90CAF9)
    val colorEncabezado = Color(0xFF3F51B5)
    val colorCard = Color(0xFFE1F5FE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
    ) {
        // 1. Encabezado superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(colorEncabezado),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (rack != null) "Rack N° ${rack.id}" else "Cargando rack...",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Tarjeta principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorCard),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            if (rack == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colorEncabezado)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // 3. Tabla de datos
                    FilaDetalle(etiqueta = "Modelo:", valor = rack.modelo, fondoOscuro = false)
                    FilaDetalle(etiqueta = "Fecha y hora:", valor = rack.fecha_hora, fondoOscuro = true)
                    FilaDetalle(etiqueta = "Responsable:", valor = rack.responsable, fondoOscuro = false)
                    FilaDetalle(etiqueta = "Estado actual:", valor = rack.estadoActual, fondoOscuro = true)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 4. Descripción
                    Text(
                        text = "Descripcion:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE8EAF6))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = rack.descripcion,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 5. Fotografía Dinámica
                    Text(
                        text = "Fotografia del rack:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val context = LocalContext.current
                        val nombreImagen = rack.fotoUri ?: "sin_imagen"

                        val imageResId = context.resources.getIdentifier(
                            nombreImagen,
                            "drawable",
                            context.packageName
                        )

                        val recursoFinal = if (imageResId != 0) imageResId else R.drawable.tulio

                        Image(
                            painter = painterResource(id = recursoFinal),
                            contentDescription = "Fotografía del rack",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(130.dp)
                                .height(200.dp)
                                .background(Color.Gray)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 6. Botón Volver
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = colorEncabezado),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Volver", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun FilaDetalle(etiqueta: String, valor: String, fondoOscuro: Boolean) {
    val colorFondo = if (fondoOscuro) Color(0xFFC5CAE9) else Color(0xFFE8EAF6)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorFondo)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valor,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}