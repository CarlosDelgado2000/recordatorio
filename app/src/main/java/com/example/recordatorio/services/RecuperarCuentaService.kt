package com.example.recordatorio.services

import com.example.recordatorio.repositories.UserRepository

class RecuperarCuentaService(private val repository: UserRepository) {
    fun recoverAccount(email: String, code: String, newPassword: String): Boolean {
        return repository.recoverAccount(email, code, newPassword)
    }
}
