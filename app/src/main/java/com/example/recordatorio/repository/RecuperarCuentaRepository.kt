package com.example.recordatorio.repository

class RecuperarCuentaRepository {
    // Simulación de una función de recuperación
    fun recuperarCuenta(email: String): Boolean {
        // Aquí implementarías la lógica para recuperar la cuenta (API, DB, etc.)
        // Retornamos true como simulación de éxito
        return email.isNotEmpty() // Simula la validación básica
    }
}
