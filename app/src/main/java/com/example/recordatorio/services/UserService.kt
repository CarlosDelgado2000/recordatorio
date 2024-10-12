package com.example.recordatorio.services

import com.example.recordatorio.repositories.UserRepository

class UserService(private val userRepository: UserRepository) {
    fun verifyUser(email: String, password: String): Boolean {
        // Lógica para verificar el usuario
        return userRepository.register(email, password)
    }
}
