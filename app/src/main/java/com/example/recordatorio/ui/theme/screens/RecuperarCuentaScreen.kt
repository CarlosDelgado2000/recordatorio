package com.example.recordatorio.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recordatorio.services.RecuperarCuentaService

@Composable
fun RecuperarCuentaScreen(
    service: RecuperarCuentaService, // Añadir este parámetro
    onBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Atrás")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Recuperar Cuenta",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Código de recuperación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isNotEmpty() && code.isNotEmpty() && newPassword.isNotEmpty()) {
                    val isRecovered = service.recoverAccount(email, code, newPassword)
                    message = if (isRecovered) "Contraseña recuperada exitosamente." else "Error al recuperar la cuenta."
                } else {
                    message = "Por favor, completa todos los campos."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar Cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotEmpty()) {
            Text(text = message)
        }
    }
}
