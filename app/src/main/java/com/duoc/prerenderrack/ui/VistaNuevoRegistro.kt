package com.duoc.prerenderrack.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.util.NotificacionHelper
import com.duoc.prerenderrack.viewmodel.RackViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun VistaNuevoRegistro(
    navController: NavController,
    rackViewModel: RackViewModel = viewModel()
) {
    val context = LocalContext.current
    val cargando by rackViewModel.cargando.collectAsState()
    val mensajeErrorVm by rackViewModel.mensajeError.collectAsState()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var modelo by remember { mutableStateOf("") }
    var fechaHora by remember { mutableStateOf("") }
    var responsable by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    val opcionesEstado = listOf(
        "Pendiente",
        "En Revision",
        "Finalizado"
    )
    var estadoSeleccionado by remember { mutableStateOf(0) }
    var modalError by remember { mutableStateOf(false) }
    var mensajeModal by remember { mutableStateOf("") }

    // Solicitud de permiso de notificaciones (Android 13+)
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* El resultado se valida en NotificacionHelper antes de notificar */ }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tienePermiso = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!tienePermiso) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // Lanzador de cámara
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && imageUri != null) {
            val bmp = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            bitmap = bmp
        }
    }

    // Lanzador de permiso de cámara
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
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
    ) {
        // 1. Encabezado
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFF3B59C8))
                    .padding(start = 20.dp, top = 35.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
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

        // 2. Campo Modelo
        item {
            Text(
                text = "Modelo:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = modelo,
                onValueChange = { modelo = it },
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

        // 3. Campo Fecha/Hora
        item {
            Text(
                text = "Fecha/Hora:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = fechaHora,
                onValueChange = { fechaHora = it },
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

        // 4. Campo Responsable
        item {
            Text(
                text = "Responsable:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = responsable,
                onValueChange = { responsable = it },
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

        // 5. Campo Descripción
        item {
            Text(
                text = "Descripción:",
                color = Color(0xFF1E2749),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 6.dp)
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
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
        }

        // 6. Fotografía, Estado y Botones de acción
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Fotografía del Rack:",
                    color = Color(0xFF1E2749),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 10.dp, bottom = 6.dp)
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

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Estado Actual:",
                        color = Color(0xFF1E2749),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 10.dp, bottom = 6.dp)
                    )
                    opcionesEstado.forEachIndexed { index, opcion ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = estadoSeleccionado == index,
                                onClick = { estadoSeleccionado = index }
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
                        enabled = !cargando,
                        onClick = {
                            when {
                                modelo.isBlank() -> {
                                    mensajeModal = "El modelo del rack no debe estar vacío."
                                    modalError = true
                                }
                                fechaHora.isBlank() -> {
                                    mensajeModal = "La fecha de registro no debe estar vacía."
                                    modalError = true
                                }
                                responsable.isBlank() -> {
                                    mensajeModal = "El nombre del responsable no debe estar vacío."
                                    modalError = true
                                }
                                descripcion.isBlank() -> {
                                    mensajeModal = "La descripción no debe estar vacía."
                                    modalError = true
                                }
                                else -> {
                                    val rutaFotoLocal = bitmap?.let { bmp ->
                                        guardarBitmapEnInterno(context, bmp)
                                    }
                                    val estadoElegido = opcionesEstado[estadoSeleccionado]
                                    val modeloLimpio = modelo.trim()
                                    val responsableLimpio = responsable.trim()

                                    val nuevoRack = Rack(
                                        id = "",
                                        fecha_hora = fechaHora.trim(),
                                        modelo = modeloLimpio,
                                        responsable = responsableLimpio,
                                        estadoActual = estadoElegido,
                                        descripcion = descripcion.trim(),
                                        fotoUri = rutaFotoLocal,
                                        estadoRevision = estadoElegido
                                    )

                                    rackViewModel.crearRack(
                                        rack = nuevoRack,
                                        onExito = {
                                            NotificacionHelper.mostrarNotificacionRackGuardado(
                                                context = context,
                                                modeloRack = modeloLimpio,
                                                responsable = responsableLimpio
                                            )
                                            navController.popBackStack()
                                        }
                                    )
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C2C6B))
                    ) {
                        Text(
                            text = if (cargando) "Guardando..." else "Guardar",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

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
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

    if (modalError) {
        ModalAlerta(
            titulo = "Error",
            mensaje = mensajeModal,
            onDismiss = { modalError = false }
        )
    }

    if (mensajeErrorVm != null) {
        ModalAlerta(
            titulo = "Error al guardar",
            mensaje = mensajeErrorVm!!,
            onDismiss = { rackViewModel.limpiarError() }
        )
    }
}


private fun guardarBitmapEnInterno(context: Context, bitmap: Bitmap): String {
    val archivo = File(context.filesDir, "rack_${System.currentTimeMillis()}.jpg")
    FileOutputStream(archivo).use { salida ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, salida)
    }
    return archivo.absolutePath
}