package com.example.recordatorio.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.res.painterResource
import com.example.recordatorio.R
import com.example.recordatorio.models.Medicamento
import com.example.recordatorio.repository.MedicamentoRepository
import com.example.recordatorio.services.MedicamentoService

@Composable
fun MedicamentoScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
    onViewAgendaClick: () -> Unit
) {
    val repository = MedicamentoRepository()
    val service = MedicamentoService(repository)

    // Estado para el valor del buscador
    var searchQuery by remember { mutableStateOf("") }
    // Estado para la lista de medicamentos filtrados
    var filteredMedicamentos by remember { mutableStateOf(service.getAllMedicamentos()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Fila para la imagen y el botón de "Cerrar sesión"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1), // Cambia el ID de la imagen si es necesario
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Button(
                onClick = onLogoutClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Cerrar sesión", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buscador de medicamento
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
                // Filtrar la lista de medicamentos según el texto ingresado
                filteredMedicamentos = service.searchMedicamentos(searchQuery)
            },
            label = { Text("Buscar medicamento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            trailingIcon = {
                IconButton(onClick = {
                    buscarMedicamento(searchQuery)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = Color.Gray
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Texto motivacional
        Text(
            text = "Recuerda, cada pastilla es un paso hacia una vida más saludable.\n¡No olvides tu medicamento hoy!",
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón "Ver agenda"
        Button(
            onClick = onViewAgendaClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver agenda", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Lista de medicamentos filtrados
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredMedicamentos) { medicamento ->
                Text(
                    text = medicamento.nombre,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Imagen debajo del botón
        Image(
            painter = painterResource(id = R.drawable.img_2), // Cambia el ID de la imagen si es necesario
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

// Función que maneja la búsqueda del medicamento
private fun buscarMedicamento(query: String) {
    if (query.isNotBlank()) {
        println("Buscando medicamento: $query") // Solo un ejemplo
    } else {
        println("Por favor, ingrese un medicamento para buscar.")
    }
}
