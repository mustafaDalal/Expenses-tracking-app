package com.example.financepal.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    HOME,
    SETTINGS,
}
sealed class NavigationItem(val route: String, val icon : ImageVector) {
    object Home : NavigationItem(Screen.HOME.name, Icons.Default.Home)
    object Settings : NavigationItem(Screen.SETTINGS.name, Icons.Default.Settings)

}