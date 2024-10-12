package com.example.recordatorio.services

import com.example.recordatorio.models.Medicamento
import com.example.recordatorio.repository.MedicamentoRepository

class MedicamentoService(private val repository: MedicamentoRepository) {
    fun getAllMedicamentos(): List<Medicamento> {
        return repository.getMedicamentos()
    }

    fun searchMedicamentos(query: String): List<Medicamento> {
        return repository.searchMedicamentos(query)
    }
}
