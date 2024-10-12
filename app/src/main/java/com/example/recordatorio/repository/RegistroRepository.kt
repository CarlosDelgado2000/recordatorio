package com.example.recordatorio.repositories

import com.example.recordatorio.models.Usuario

class RegistroRepository {
    private val usuarios = mutableListOf<Usuario>() // Simula una base de datos

    fun register(usuario: Usuario): Boolean {
        // Verifica si el correo ya está registrado
        if (usuarios.any { it.email == usuario.email }) {
            return false // El usuario ya existe
        }
        usuarios.add(usuario) // Agrega el nuevo usuario
        return true // Registro exitoso
    }
}
