package br.edu.ifal.casajunta.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.components.NavigationRowCard
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun PantryCard(
    pantryMissingCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRowCard(
        icon = {
            Icon(
                imageVector = Icons.Filled.Inventory2,
                contentDescription = null,
                modifier = Modifier.size(38.dp),
                tint = CasaJuntaTheme.extendedColors.warning,
            )
        },
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.home_pantry_title),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = if (pantryMissingCount == 0) {
                stringResource(R.string.home_pantry_none)
            } else {
                pluralStringResource(
                    R.plurals.home_pantry_missing,
                    pantryMissingCount,
                    pantryMissingCount,
                )
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (pantryMissingCount > 0) {
            Text(
                text = pluralStringResource(
                    R.plurals.home_pantry_restock,
                    pantryMissingCount,
                ),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}
