# Sistema de Registro de Racks (PreRenderRack)

Aplicación móvil Android desarrollada en Kotlin y Jetpack Compose para el registro, seguimiento y control del estado operativo de racks en datacenters. La aplicación se conecta a un backend en **Xano** mediante una API REST y gestiona sesiones locales con `SharedPreferences`.

---

## Stack Tecnológico

* **Lenguaje:** Kotlin
* **Interfaz de Usuario:** Jetpack Compose (Material 3)
* **Navegación:** Jetpack Navigation Compose
* **Consumo de API:** Retrofit 2 + Gson Converter
* **Backend / Base de Datos:** Xano (API REST)
* **Persistencia Local:** SharedPreferences (gestión de sesión activa)
* **Pruebas Unitarias:** JUnit 4, Kotlinx Coroutines Test, OkHttp MockWebServer

---

## Roles y Funcionalidades

El sistema cuenta con control de acceso basado en dos perfiles principales:

### 1. Inspector
* Inicio y cierre de sesión con persistencia local.
* Visualización de últimos logs y acceso al historial completo de racks.
* Creación de nuevos registros de estado de racks.

### 2. Supervisor
* Todas las funcionalidades del perfil Inspector.
* **Gestión de Usuarios (CRUD):** Creación, edición y eliminación de cuentas de usuario dentro del sistema.

---

## Modelos de Datos

### Registro de Rack (`Rack`)
Al crear o consultar un registro de rack se gestionan los siguientes campos:

| Campo | Tipo | Descripción |
| :--- | :--- | :--- |
| `id` | `String` | Identificador único del registro. |
| `modelo` | `String` | Modelo o código identificador del rack. |
| `fecha_hora` | `String` | Fecha y hora en que se realizó el log. |
| `responsable` | `String` | Nombre de la persona a cargo del registro. |
| `descripcion` | `String` | Detalle del estado actual u observaciones técnicas. |
| `fotoUri` | `String?` | URI o ruta de la fotografía adjunta del rack (opcional). |
| `estadoActual` / `estadoRevision` | `String` | Estado del proceso: **Pendiente**, **En revisión** o **Finalizado**. |

### Usuario (`Usuario`)
Para el registro y administración de personal se utilizan los siguientes atributos:

| Campo | Tipo | Descripción |
| :--- | :--- | :--- |
| `rut` | `String` | Identificador único (Clave primaria). |
| `nombre` | `String` | Nombre y apellidos del usuario. |
| `fechaNacimiento` | `String` | Fecha de nacimiento. |
| `numeroTelefono` | `String` | Teléfono de contacto. |
| `correo` | `String` | Correo electrónico institucional (usado para el login). |
| `contrasena` | `String` | Contraseña de acceso. |
| `cargo` | `String` | Rol asignado: **Inspector** o **Especialista Técnico**. |

---

## Endpoints de la API (Xano)

La comunicación con el servidor se realiza a través de interfaces Retrofit (`RackApiService` y `UsuarioApiService`):

### Racks
* `GET /racks` — Obtiene el listado completo de registros de racks.
* `GET /racks/{id}` — Obtiene el detalle de un rack específico.
* `POST /racks` — Crea un nuevo registro de rack.
* `PATCH /racks/{id}` — Actualiza los datos de un rack existente.
* `DELETE /racks/{id}` — Elimina un registro de rack.

### Usuarios
* `GET /usuarios` — Obtiene el listado de usuarios registrados.
* `GET /usuarios/{id}` — Obtiene un usuario por su identificador.
* `POST /usuarios` — Registra un nuevo usuario.
* `PATCH /usuarios/{id}` — Edita la información de un usuario.
* `DELETE /usuarios/{id}` — Elimina un usuario del sistema.

---

### Estructura del Proyecto

```text
com.duoc.prerenderrack
├── model/               # Clases de datos (Rack, Usuario)
├── remote/              # Configuración de Retrofit e interfaces de API (Xano)
├── repository/          # Repositorios de datos y manejo de sesión (RackRepository, UsuarioRepository)
├── ui/                  # Pantallas y componentes reutilizables en Jetpack Compose
└── viewmodel/           # Gestión de estado y lógica de presentación (ViewModels)

com.duoc.prerenderrack(test)
└──repository/           # Pruebas Unitarias de Racks y Usuarios a través de repositorios.
```

---
## Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/tu-repositorio.git](https://github.com/tu-usuario/tu-repositorio.git)
   ```
2. Abrir el proyecto en **Android Studio**.
3. Esperar a que Gradle sincronice todas las dependencias.
4. Verificar la URL base de la instancia de Xano en `remote/RetrofitInstance.kt`.
5. Ejecutar la aplicación en un emulador o dispositivo físico con Android (API 24+).

---

## Pruebas Unitarias

El proyecto incluye pruebas unitarias locales para la capa de repositorios (`RackRepositoryTest` y `UsuarioRepositoryTest`) ubicadas en `app/src/test/java/com/duoc/prerenderrack/repository/`.

Las pruebas utilizan **MockWebServer** para simular las respuestas HTTP de Xano sin depender de conexión a internet, validando:
* La correcta construcción de peticiones HTTP (`GET`, rutas).
* La deserialización de respuestas JSON a objetos Kotlin (`Rack` y `Usuario`).
* La propagación controlada de errores HTTP (ej. código `500`).

Para ejecutar las pruebas desde la terminal:
```bash
./gradlew testDebugUnitTest
```




