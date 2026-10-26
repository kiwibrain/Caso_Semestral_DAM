package com.duoc.prerenderrack.ui

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import java.io.File

@Composable
fun VistaNuevoRegistro(navController: NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var permisoConcedido by remember { mutableStateOf(false) }
    var modelo by remember { mutableStateOf("") }
    var fecha_hora by remember { mutableStateOf("") }
    var responsable by remember { mutableStateOf("") }
    var estadoActual by remember { mutableStateOf("") }
    var descipcion by remember { mutableStateOf("") }

    // Lanzador de cámara
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && imageUri != null) {
            val bmp = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            bitmap = bmp
        }
    }

    // Permiso de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        permisoConcedido = granted
        if (granted) {
            val file = File.createTempFile("profile_photo", ".jpg", context.cacheDir)
            file.deleteOnExit()
            val uri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                file
            )
            imageUri = uri
            takePictureLauncher.launch(uri)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF88BBFF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Box encabezado
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF3B59C8)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nuevo Registro",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        //Fin encabezado

        // 2. Modelo
        item {
            Text(
                text = "Modelo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = modelo,
                onValueChange = { modelo = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
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
        //Fin text field Modelo

        // 3. Fecha/hora
        item {
            Text(
                text = "Fecha/Hora:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = fecha_hora,
                onValueChange = { fecha_hora = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
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
        //Fin text field fecha/hora

        // 4. Responsable
        item {
            Text(
                text = "Responsable:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = responsable,
                onValueChange = { responsable = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
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
        //Fin text field  responsable

        // 5. Estado Actual
        item {
            Text(
                text = "Estado Actual:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = estadoActual,
                onValueChange = { estadoActual = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
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
        } //Fin text field Estado Actual

        // 6. Descripcion
        item {
            Text(
                text = "Descripcion:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = descipcion,
                onValueChange = { descipcion = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
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
        } // Fin text field descripcion

        // 7. Foto del Rack y Botones
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Fotografia del Rack:",
                    color = Color(0xFF1E2749),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 6.dp)
                )

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "Foto del Rack",
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(onClick = {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }) {
                    Text("Tomar foto del Rack")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("menu-inspector") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Guardar",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                //Fin boton guardar

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("menu-inspector") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Volver",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                //Fin boton volver

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

    }
}