package br.edu.ifal.casajunta.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong as FilledReceiptLong
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong as OutlinedReceiptLong
import androidx.compose.material.icons.filled.CheckBox as FilledCheckBox
import androidx.compose.material.icons.filled.Group as FilledGroup
import androidx.compose.material.icons.filled.Home as FilledHome
import androidx.compose.material.icons.filled.Kitchen as FilledKitchen
import androidx.compose.material.icons.outlined.CheckBox as OutlinedCheckBox
import androidx.compose.material.icons.outlined.Group as OutlinedGroup
import androidx.compose.material.icons.outlined.Home as OutlinedHome
import androidx.compose.material.icons.outlined.Kitchen as OutlinedKitchen
import androidx.compose.ui.graphics.vector.ImageVector
import br.edu.ifal.casajunta.R

enum class TopLevelDestination(
    @param:StringRes val labelRes: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
) {
    Overview(
        labelRes = R.string.nav_overview,
        unselectedIcon = Icons.Outlined.OutlinedHome,
        selectedIcon = Icons.Filled.FilledHome,
    ),
    Tasks(
        labelRes = R.string.nav_tasks,
        unselectedIcon = Icons.Outlined.OutlinedCheckBox,
        selectedIcon = Icons.Filled.FilledCheckBox,
    ),
    Finances(
        labelRes = R.string.nav_finances,
        unselectedIcon = Icons.AutoMirrored.Outlined.OutlinedReceiptLong,
        selectedIcon = Icons.AutoMirrored.Filled.FilledReceiptLong,
    ),
    Pantry(
        labelRes = R.string.nav_pantry,
        unselectedIcon = Icons.Outlined.OutlinedKitchen,
        selectedIcon = Icons.Filled.FilledKitchen,
    ),
    Members(
        labelRes = R.string.nav_members,
        unselectedIcon = Icons.Outlined.OutlinedGroup,
        selectedIcon = Icons.Filled.FilledGroup,
    ),
}
