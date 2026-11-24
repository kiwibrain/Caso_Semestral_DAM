package com.duoc.prerenderrack.repository

import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.remote.RackApiService
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

class RackRepositoryTest {
    private lateinit var servidor: MockWebServer
    private lateinit var repository: RackRepository

    @Before
    fun preparar() {
        servidor = MockWebServer()
        servidor.start()

        val api = Retrofit.Builder()
            .baseUrl(servidor.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RackApiService::class.java)

        repository = RackRepository(api)
    }

    @After
    fun limpiar() {
        servidor.shutdown()
    }

    @Test
    fun `consulta el endpoint de racks y convierte la respuesta`() = runTest {
        println("INICIO: comprobar la solicitud y la conversion del JSON de racks.")
        println("PREPARACION: el servidor local simulado respondera con un Rack.")
        servidor.enqueue(
            MockResponse().setBody(
                """
                [
                  {
                    "id": "67",
                    "fecha_hora": "24-03-2026 - 09:35 hrs",
                    "modelo": "0000023634AAA",
                    "responsable": "Elsa Pato",
                    "estadoActual": "En revisión",
                    "descripcion": "Rack operativo",
                    "fotoUri": null,
                    "estadoRevision": "Pendiente"
                  }
                ]
                """.trimIndent()
            )
        )

        println("ACCION: el repositorio llama al servidor local, no a Xano.")
        val resultado = repository.obtenerRacks()

        println("COMPROBACION: el JSON debe convertirse en un Rack y usar GET.")
        val esperado = listOf(
            Rack(
                id = "67",
                fecha_hora = "24-03-2026 - 09:35 hrs",
                modelo = "0000023634AAA",
                responsable = "Elsa Pato",
                estadoActual = "En revisión",
                descripcion = "Rack operativo",
                fotoUri = null,
                estadoRevision = "Pendiente"
            )
        )
        assertEquals(esperado, resultado)

        val solicitud = servidor.takeRequest()
        assertEquals("GET", solicitud.method)
        // Ajusta "/rack" a la ruta exacta que tengas en tu anotación @GET(...)
        assertEquals("/racks", solicitud.path)
        println("CORRECTO: la solicitud y el rack son los esperados.")
    }

    @Test
    fun `propaga errores http al obtener racks`() = runTest {
        println("INICIO: comprobar la propagacion de errores HTTP en RackRepository.")
        println("PREPARACION: el servidor local simulado respondera con HTTP 500.")
        servidor.enqueue(MockResponse().setResponseCode(500))

        println("ACCION: solicitar racks al servidor local.")
        try {
            repository.obtenerRacks()
            throw AssertionError("Se esperaba un HttpException")
        } catch (error: HttpException) {
            println("COMPROBACION: el error recibido debe tener codigo 500.")
            assertEquals(500, error.code())
        }
        println("CORRECTO: el repositorio propago el error HTTP 500.")
    }
}