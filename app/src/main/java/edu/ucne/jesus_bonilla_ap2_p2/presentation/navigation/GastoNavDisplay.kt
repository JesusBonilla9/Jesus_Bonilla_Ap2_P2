package edu.ucne.jesus_bonilla_ap2_p2.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.detail.DetailGastoScreen
import edu.ucne.jesus_bonilla_ap2_p2.presentation.gasto.list.GastoListScreen

@Composable
fun GastoNavDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
) {
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(innerPadding),
        entryProvider = entryProvider {
            entry<Screen.GastoList> {
                GastoListScreen(
                    onAddGasto = {
                        backStack.add(Screen.GastoDetail(0))
                    },
                    onEditGasto = { id ->
                        backStack.add(Screen.GastoDetail(id))
                    }
                )
            }

            entry<Screen.GastoDetail> { key ->
                DetailGastoScreen(
                    gastoId = key.id,
                    onNavigateBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }
        }
    )
}
