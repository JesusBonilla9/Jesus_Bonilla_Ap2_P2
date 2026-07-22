package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoListScreen(
    viewModel: GastoListViewModel = hiltViewModel(),
    onAddGasto: () -> Unit,
    onEditGasto: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Gastos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddGasto) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Gasto")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.error != null) {
                Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState.gastos) { gasto ->
                        ListItem(
                            headlineContent = { Text(gasto.suplidor) },
                            supportingContent = { Text("ID: ${gasto.gastoId} - Fecha: ${gasto.fecha}") },
                            trailingContent = { Text("$${gasto.monto}") },
                            modifier = Modifier.clickable { onEditGasto(gasto.gastoId) }
                        )
                        HorizontalDivider()
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val total = uiState.gastos.sumOf { it.monto }
                val count = uiState.gastos.size

                Text(text = "Conteo: $count", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Total: $$total", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
