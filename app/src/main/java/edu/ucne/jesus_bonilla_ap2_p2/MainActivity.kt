package edu.ucne.jesus_bonilla_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.rememberNavBackStack
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.jesus_bonilla_ap2_p2.presentation.navigation.GastoNavDisplay
import edu.ucne.jesus_bonilla_ap2_p2.presentation.navigation.Screen
import edu.ucne.jesus_bonilla_ap2_p2.ui.theme.Jesus_Bonilla_Ap2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jesus_Bonilla_Ap2_P2Theme {
                val backStack = rememberNavBackStack(Screen.GastoList)
                val items = listOf(
                    TopLevelRoute("Gastos", Screen.GastoList, Icons.AutoMirrored.Filled.List)
                )

                Scaffold(
                    bottomBar = {
                        val currentDestination = backStack.lastOrNull()
                        val isDetail = currentDestination is Screen.GastoDetail

                        if (!isDetail) {
                            NavigationBar {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination == item.ruta,
                                        onClick = {
                                            if (currentDestination != item.ruta) {
                                                backStack.clear()
                                                backStack.add(item.ruta)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    GastoNavDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

data class TopLevelRoute<T : Screen>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)
