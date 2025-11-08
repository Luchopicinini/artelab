# ArteLab App
CASO Y ALCANCE
Elegimos el caso ArteLab SPA, porque basicamete nos intereso la parte del arte, que el desafio basicamente es crear un sistema modular para ventas y promociones.

El alcance de la app contiene las sioguientes funcionalidades:
- Inicio sesion de usuarios
- Gestion de perfil con cambio de datos e imagen (Mantenemos la misma foto)
- Navegacion interna usando arquitectura MVVM y Jetpack Compose
- Pantalla princimar (home) con navegación interna
- Persistencia local simple
- Animaciones suaves
------------------------------------------
  REQUISITOS Y EJECUCIÓN
- Lenguaje: Kotlin
- Arquitectura: MVVM
- Persistencia local: Datastore
- Instalación
1. Fijarse tener instalado jdk 11, gradle incluido, emulador android, luego clonar git clone https://github.com/Luchopicinini/artelab.git en la terminal.
------------------------------------------

ARQUITECTURA Y FLUJO:
├───main
│       │   │   AndroidManifest.xml
│       │   │
│       │   ├───java
│       │   │   └───com
│       │   │       └───localgo
│       │   │           └───artelab
│       │   │               │   AppDependencies.kt
│       │   │               │   Dependecies.kt
│       │   │               │   MainActivity.kt
│       │   │               │
│       │   │               ├───data
│       │   │               │   ├───local
│       │   │               │   │   │   SessionManager.kt
│       │   │               │   │   │   UserSessionManager.kt
│       │   │               │   │   │
│       │   │               │   │   ├───dao
│       │   │               │   │   │       UserDao.kt
│       │   │               │   │   │
│       │   │               │   │   ├───database
│       │   │               │   │   │       AppDatabase.kt
│       │   │               │   │   │
│       │   │               │   │   └───entity
│       │   │               │   │           User.kt
│       │   │               │   │
│       │   │               │   ├───remote
│       │   │               │   │   │   ApiService.kt
│       │   │               │   │   │   AuthInterceptor.kt
│       │   │               │   │   │   RetrofitClient.kt
│       │   │               │   │   │
│       │   │               │   │   └───dto
│       │   │               │   │           LoginRequest.kt
│       │   │               │   │           LoginResponse.kt
│       │   │               │   │           UserDto.kt
│       │   │               │   │           UsersResponse.kt
│       │   │               │   │
│       │   │               │   └───repository
│       │   │               │           AvatarRepository.kt
│       │   │               │           UserRepository.kt
│       │   │               │
│       │   │               ├───model
│       │   │               │       LoginUiState.kt
│       │   │               │       ProfileUiState.kt
│       │   │               │
│       │   │               ├───ui
│       │   │               │   ├───components
│       │   │               │   │       ImagePickerDialog.kt
│       │   │               │   │
│       │   │               │   ├───navigation
│       │   │               │   │       AppNavigation.kt
│       │   │               │   │
│       │   │               │   ├───screens
│       │   │               │   │       HomeScreen.kt
│       │   │               │   │       LoginScreen.kt
│       │   │               │   │       ProfileScreen.kt
│       │   │               │   │       RegisterScreen.kt
│       │   │               │   │
│       │   │               │   └───theme
│       │   │               │           Color.kt
│       │   │               │           Theme.kt
│       │   │               │           Type.kt
│       │   │               │
│       │   │               ├───utils
│       │   │               │       ValidationUtils.kt
│       │   │               │
│       │   │               └───viewmodel
│       │   │                       HomeViewModel.kt
│       │   │                       LoginViewModel.kt
│       │   │                       ProfileViewModel.kt
│       │   │                       RegisterViewModel.kt
│       │   │
│       │   └───res
│       │       ├───drawable
│       │       │       ic_launcher_background.xml
│       │       │       ic_launcher_foreground.xml
│       │       │
│       │       ├───mipmap-anydpi-v26
│       │       │       ic_launcher.xml
│       │       │       ic_launcher_round.xml
│       │       │
│       │       ├───mipmap-hdpi
│       │       │       ic_launcher.webp
│       │       │       ic_launcher_round.webp
│       │       │
│       │       ├───mipmap-mdpi
│       │       │       ic_launcher.webp
│       │       │       ic_launcher_round.webp
│       │       │
│       │       ├───mipmap-xhdpi
│       │       │       ic_launcher.webp
│       │       │       ic_launcher_round.webp
│       │       │
│       │       ├───mipmap-xxhdpi
│       │       │       ic_launcher.webp
│       │       │       ic_launcher_round.webp
│       │       │
│       │       ├───mipmap-xxxhdpi
│       │       │       ic_launcher.webp
│       │       │       ic_launcher_round.webp
│       │       │
│       │       ├───values
│       │       │       colors.xml
│       │       │       strings.xml
│       │       │       themes.xml
│       │       │
│       │       └───xml
│       │               backup_rules.xml
│       │               data_extraction_rules.xml
│       │               file_paths.xml
│       │
│       └───test
│           └───java
│               └───com
│                   └───localgo
│                       └───artelab
│                               ExampleUnitTest.kt
│
├───data
│   ├───local
│   │   │   SessionManager.kt
│   │   │
│   │   ├───dao
│   │   │       UserDao.kt
│   │   │
│   │   ├───database
│   │   │       AppDatabase.kt
│   │   │
│   │   └───entity
│   │           User.kt
│   │
│   └───remote
│       │   ApiService.kt
│       │   AuthInterceptor.kt
│       │   RetrofitClient.kt
│       │
│       └───dto
│               LoginRequest.kt
│               LoginResponse.kt
│               UserDto.kt
│               UsersResponse.kt
│
├───gradle
│   │   libs.versions.toml
│   │
│   └───wrapper
│           gradle-wrapper.jar
│           gradle-wrapper.properties
│
├───repository
│       UserRepository.kt
│
├───ui
│   ├───navigation
│   │       AppNavigation.kt
│   │
│   └───screens
│           HomeScreen.kt
│           LoginScreen.kt
│           ProfileScreen.kt
│           RegisterScreen.kt
│
├───utils
│       ValidationUtils.kt
│
└───viewmodel
        HomeViewModel.kt
        LoginViewModel.kt
        ProfileViewModel.kt
        RegisterViewModel.kt


- Gestion de estado
Hemos usado el gestion de estado en formularios, inputs de perfil y cambios de avatar
 val email by viewModel.email.collectAsState()
val password by viewModel.password.collectAsState()
val errorMessage by viewModel.errorMessage.collectAsState()

- Estado global
  Lo hermos implementado en usuario autenticado, token jwt, resultados de login, datos de perfil cargados desde /auth/me.

-Flujos de datos 
Seria UI -> ViewModel -> Repository -> (API o DataStore)
Repository -> ViewModel -> UI
La UI no llama directamente a la API, el repositorio decide si usar remoto o local y la UI solo reacciona a state

-Navegación
La app usa Navigation Compose con un grafo simple de rutas, que seria asi:
Login -> home -> profile

-Tabs no son necesarios en este caso, pero la arquitectura lo permite facilmente creando un BottonNavigationBar
------------------------------------------

FUNCIONALIDADES

-Formulario validado (login/Registro)
La app inmcluye formularios para Login y Registro, ambos con validaciones:
Email con formato correcto, Campos obligatorios, indicadores visuales de error.

- Navegación y Backstack
Se utiliza Navigation Compose con un grafo que incluye (Login, register, home y profile)


