package edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailGastoScreen(
    gastoId: Int,
    viewModel: DetailGastoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(gastoId) {
        if (gastoId > 0) {
            viewModel.getGasto(gastoId)
        } else {
            viewModel.resetState()
        }
    }

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (gastoId == 0) "Nuevo Gasto" else "Editar Gasto") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::saveGasto) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
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
            } else {
                OutlinedTextField(
                    value = uiState.suplidor,
                    onValueChange = viewModel::onSuplidorChange,
                    label = { Text("Suplidor") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.error?.contains("suplidor") == true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.fecha,
                    onValueChange = viewModel::onFechaChange,
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.ncf,
                    onValueChange = viewModel::onNcfChange,
                    label = { Text("NCF") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.itbis.toString(),
                    onValueChange = viewModel::onItbisChange,
                    label = { Text("ITBIS") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.monto.toString(),
                    onValueChange = viewModel::onMontoChange,
                    label = { Text("Monto") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.error?.contains("monto") == true
                )

                if (uiState.error != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = uiState.error!!, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
