package com.example.recordatorio.services

import com.example.recordatorio.models.Usuario
import com.example.recordatorio.repositories.RegistroRepository

class RegistroService(private val repository: RegistroRepository) {
    fun registerAccount(email: String, password: String, confirmPassword: String): Boolean {
        // Verifica que las contraseñas coincidan
        if (password != confirmPassword) {
            return false // Las contraseñas no coinciden
        }

        // Crea el objeto Usuario
        val usuario = Usuario(email, password)

        // Intenta registrar el usuario
        return repository.register(usuario)
    }
}
