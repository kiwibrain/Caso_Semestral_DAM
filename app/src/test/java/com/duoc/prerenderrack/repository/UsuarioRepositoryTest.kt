package com.duoc.prerenderrack.repository

import com.duoc.prerenderrack.model.Usuario
import com.duoc.prerenderrack.remote.UsuarioApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsuarioRepositoryTest {
    private lateinit var servidor: MockWebServer
    private lateinit var repository: UsuarioRepository

    @Before
    fun preparar() {
        servidor = MockWebServer()
        servidor.start()

        val api = Retrofit.Builder()
            .baseUrl(servidor.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApiService::class.java)

        repository = UsuarioRepository(context = null, api = api)
    }

    @After
    fun limpiar() {
        servidor.shutdown()
    }

    @Test
    fun `consulta el endpoint de usuarios y convierte la respuesta`() = runTest {
        println("INICIO: comprobar la solicitud y la conversion del JSON de usuarios.")
        println("PREPARACION: el servidor local simulado respondera con un Usuario.")
        servidor.enqueue(
            MockResponse().setBody(
                """
                [
                  {
                    "rut": "12345678-9",
                    "nombre": "Tulio Triviño",
                    "fechaNacimiento": "1980-05-12",
                    "numeroTelefono": "+56912345678",
                    "correo": "supervisor@duoc.cl",
                    "contrasena": "4321",
                    "cargo": "Supervisor"
                  }
                ]
                """.trimIndent()
            )
        )

        println("ACCION: el repositorio llama al servidor local, no a Xano.")
        val resultado = repository.obtenerUsuarios()

        println("COMPROBACION: el JSON debe convertirse en un Usuario y usar GET.")
        val esperado = listOf(
            Usuario(
                rut = "12345678-9",
                nombre = "Tulio Triviño",
                fechaNacimiento = "1980-05-12",
                numeroTelefono = "+56912345678",
                correo = "supervisor@duoc.cl",
                contrasena = "4321",
                cargo = "Supervisor"
            )
        )
        assertEquals(esperado, resultado)

        val solicitud = servidor.takeRequest()
        assertEquals("GET", solicitud.method)
        assertEquals("/usuarios", solicitud.path)
        println("CORRECTO: la solicitud y el usuario son los esperados.")
    }

    @Test
    fun `propaga errores http al obtener usuarios`() = runTest {
        println("INICIO: comprobar la propagacion de errores HTTP en UsuarioRepository.")
        println("PREPARACION: el servidor local simulado respondera con HTTP 500.")
        servidor.enqueue(MockResponse().setResponseCode(500))

        println("ACCION: solicitar usuarios al servidor local.")
        try {
            repository.obtenerUsuarios()
            throw AssertionError("Se esperaba un HttpException")
        } catch (error: HttpException) {
            println("COMPROBACION: el error recibido debe tener codigo 500.")
            assertEquals(500, error.code())
        }
        println("CORRECTO: el repositorio propago el error HTTP 500.")
    }
}