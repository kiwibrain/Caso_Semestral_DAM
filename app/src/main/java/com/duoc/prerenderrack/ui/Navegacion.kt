package com.duoc.prerenderrack.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pantalla_inicial"){
        composable("pantalla_inicial") { PantallaInicial(navController)}
        composable("pantalla_login") { PantallaLogin(navController)}
        composable("menu-inspector") {MenuInspector(navController)}
        composable("nuevo_registro") {VistaNuevoRegistro(navController)}
        composable("nuevo_usuario") {VistaCreacionUsuario(navController)}
        composable("menu-supervisor"){MenuSupervisor(navController)}
        //agregar mas pantallas mas tarde
    }
}