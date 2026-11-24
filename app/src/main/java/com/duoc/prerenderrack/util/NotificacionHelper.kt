package com.duoc.prerenderrack.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object NotificacionHelper {

    private const val CHANNEL_ID = "canal_racks_notificaciones"
    private const val CHANNEL_NAME = "Registro de Racks"
    private const val CHANNEL_DESCRIPTION = "Notificaciones al registrar o actualizar racks"


    private fun crearCanalSiEsNecesario(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }


      //Muestra una notificación confirmando el registro de un nuevo rack.

    fun mostrarNotificacionRackGuardado(
        context: Context,
        modeloRack: String,
        responsable: String
    ) {
        crearCanalSiEsNecesario(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tienePermiso = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!tienePermiso) return
        }

        val notificacion = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Puedes cambiarlo por tu ícono en drawable
            .setContentTitle("Nuevo Rack Registrado")
            .setContentText("Modelo: $modeloRack | Responsable: $responsable")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationId = System.currentTimeMillis().toInt()
        NotificationManagerCompat.from(context).notify(notificationId, notificacion)
    }
}