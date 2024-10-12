package com.example.recordatorio

import PortadaScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.recordatorio.services.RecuperarCuentaService
import com.example.recordatorio.services.RegistroService
import com.example.recordatorio.services.UserService
import com.example.recordatorio.repositories.UserRepository
import com.example.recordatorio.repositories.RegistroRepository
import com.example.recordatorio.ui.theme.RecordatorioTheme
import com.example.recordatorio.ui.theme.screens.LoginScreen
import com.example.recordatorio.ui.theme.screens.RecuperarCuentaScreen
import com.example.recordatorio.ui.theme.screens.RegistroScreen
import com.example.recordatorio.ui.theme.screens.MedicamentoScreen

class MainActivity : ComponentActivity() {
    private lateinit var userService: UserService
    private lateinit var recuperarCuentaService: RecuperarCuentaService
    private lateinit var registroService: RegistroService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear la instancia de UserRepository, RegistroRepository, UserService y RecuperarCuentaService
        val userRepository = UserRepository()
        val registroRepository = RegistroRepository()
        userService = UserService(userRepository)
        registroService = RegistroService(registroRepository)
        recuperarCuentaService = RecuperarCuentaService(userRepository)

        setContent {
            RecordatorioTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Portada) }

                when (currentScreen) {
                    is Screen.Portada -> PortadaScreen(
                        onLoginClick = { currentScreen = Screen.Login },
                        onRegisterClick = { currentScreen = Screen.Registro }
                    )
                    is Screen.Login -> LoginScreen(
                        userService = userService, // Pasa el userService aquí
                        onLoginClick = { email, password ->
                            // Lógica de autenticación
                            val isAuthenticated = userService.verifyUser(email, password)
                            if (isAuthenticated) {
                                currentScreen = Screen.Medicamento // Redirige a Medicamento en caso de éxito
                            } else {
                                // Muestra un mensaje de error
                            }
                        },
                        onBackClick = { currentScreen = Screen.Portada },
                        onRecoverClick = { currentScreen = Screen.RecuperarCuenta }
                    )
                    is Screen.RecuperarCuenta -> RecuperarCuentaScreen(
                        service = recuperarCuentaService, // Pasa el servicio de recuperación aquí
                        onBackClick = { currentScreen = Screen.Login }
                    )
                    is Screen.Registro -> RegistroScreen(
                        service = registroService, // Pasa el servicio de registro aquí
                        onBackClick = { currentScreen = Screen.Portada },
                        onRegisterSuccess = { currentScreen = Screen.Login }
                    )
                    is Screen.Medicamento -> MedicamentoScreen(
                        onViewAgendaClick = { /* Lógica para ver la agenda */ },
                        onLogoutClick = { currentScreen = Screen.Portada } // Cerrar sesión
                    )
                }
            }
        }
    }
}

// Enum para las pantallas
sealed class Screen {
    object Portada : Screen()
    object Login : Screen()
    object RecuperarCuenta : Screen()
    object Registro : Screen()
    object Medicamento : Screen() // Pantalla de medicamentos
}
