package com.duoc.prerenderrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.viewmodel.RackViewModel

@Composable
fun VistaHistorialRegistro(
    navController: NavController,
    rackViewModel: RackViewModel = viewModel()
) {
    val racks by rackViewModel.racks.collectAsState()

    // Carga los racks desde Xano al entrar a la vista
    LaunchedEffect(Unit) {
        rackViewModel.cargarRacks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
    ) {
        // ENCABEZADO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF3B59C8))
                .padding(top = 35.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Historial de Registros",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Lista logs
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF679CF6)),
            shape = RoundedCornerShape(24.dp)
        ) {
            if (racks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay registros disponibles",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(racks) { rack ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(rack.fecha_hora ?: "Sin fecha", fontSize = 12.sp, color = Color.Gray)
                                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray)
                                Text("Rack N° ${rack.id}", fontWeight = FontWeight.Bold, color = Color.Black)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    val desc = rack.descripcion ?: "Sin descripción"
                                    Text(
                                        text = if (desc.length > 20) "${desc.take(20)}..." else desc,
                                        fontSize = 14.sp
                                    )
                                    Button(
                                        onClick = { navController.navigate("detalle-rack/${rack.id}") },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B59C8)),
                                        modifier = Modifier.height(35.dp)
                                    ) {
                                        Text("Ver rack", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Volver
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B59C8)),
            modifier = Modifier
                .padding(15.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("Volver")
        }
    }
}