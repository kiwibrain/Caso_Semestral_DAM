package com.duoc.prerenderrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext

    NavHost(navController = navController, startDestination = "pantalla_inicial") {
        composable("pantalla_inicial") {
            PantallaInicial(navController)
        }
        composable("pantalla_login") {
            PantallaLogin(navController)
        }
        composable("menu-inspector") {
            MenuInspector(navController)
        }
        composable("nuevo_registro") {
            VistaNuevoRegistro(navController)
        }
        composable("nuevo_usuario") {
            VistaCreacionUsuario(navController)
        }
        composable("menu-supervisor") {
            MenuSupervisor(navController)
        }
        composable("menu-racks") {
            VistaHistorialRegistro(navController)
        }

        composable(
            route = "detalle-rack/{rackId}",
            arguments = listOf(navArgument("rackId") { type = NavType.StringType })
        ) { backStackEntry ->
            val rackId = backStackEntry.arguments?.getString("rackId") ?: ""
            DetalleRack(navController = navController, rackId = rackId)
        }

        composable("lista_usuarios") {
            ListaUsuarios(navController = navController)
        }

        composable(
            route = "editar_usuario/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.StringType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getString("usuarioId") ?: ""
            EditarUsuario(navController = navController, usuarioId = usuarioId)
        }

    }
}