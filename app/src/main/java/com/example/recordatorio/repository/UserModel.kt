package com.example.recordatorio.repositories

import com.example.recordatorio.models.User

class UserRepository {
    // Lista mutable de usuarios
    private val users = mutableListOf<User>()

    // Método para registrar un nuevo usuario
    fun register(email: String, password: String): Boolean {
        // Verifica si ya existe un usuario con el mismo correo
        val exists = users.any { it.email == email }

        return if (!exists) {
            // Si el usuario no existe, se agrega a la lista
            users.add(User(email, password))
            true // Registro exitoso
        } else {
            false // Ya existe un usuario con ese correo
        }
    }

    // Método de inicio de sesión
    fun login(email: String, password: String): Boolean {
        // Verifica si hay un usuario con el email y contraseña proporcionados
        return users.any { it.email == email && it.password == password }
    }

    // Método para recuperar cuenta
    fun recoverAccount(email: String, code: String, newPassword: String): Boolean {
        // Busca al usuario por su correo electrónico
        val user = users.find { it.email == email }

        // Verifica si el usuario existe y si el código de recuperación es correcto
        return if (user != null && code == "123456") {
            // Simulando la actualización de la contraseña
            user.password = newPassword // Actualiza la contraseña del usuario
            true // Indica que la recuperación fue exitosa
        } else {
            false // Indica que la recuperación falló
        }
    }
}
