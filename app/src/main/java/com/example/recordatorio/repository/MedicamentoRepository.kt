package com.example.recordatorio.repository

import com.example.recordatorio.models.Medicamento

class MedicamentoRepository {
    // Lista simulada de medicamentos
    private val medicamentos = mutableListOf(
        Medicamento(1, "Paracetamol"),
        Medicamento(2, "Ibuprofeno"),
        Medicamento(3, "Amoxicilina"),
        Medicamento(4, "Cetirizina"),
        Medicamento(5, "Losartán")
        // Agrega más medicamentos según sea necesario
    )

    fun getMedicamentos(): List<Medicamento> {
        return medicamentos
    }

    fun searchMedicamentos(query: String): List<Medicamento> {
        return medicamentos.filter { medicamento ->
            medicamento.nombre.contains(query, ignoreCase = true)
        }
    }
}
