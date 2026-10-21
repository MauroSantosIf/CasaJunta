package br.edu.ifal.casajunta.ui.screens.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.components.NavigationRowCard
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun BalanceCard(
    balanceText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRowCard(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AccountBalanceWallet,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        onClick = onClick,
        modifier = modifier,
        containerColor = CasaJuntaTheme.extendedColors.primaryLight,
    ) {
        Text(
            text = stringResource(R.string.home_balance_label),
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = balanceText,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}
