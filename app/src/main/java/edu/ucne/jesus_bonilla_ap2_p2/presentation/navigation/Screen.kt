package edu.ucne.jesus_bonilla_ap2_p2.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object GastoList : Screen()

    @Serializable
    data class GastoDetail(val id: Int) : Screen()
}
