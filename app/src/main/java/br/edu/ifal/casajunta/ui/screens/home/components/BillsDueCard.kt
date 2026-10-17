package br.edu.ifal.casajunta.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.components.CasaJuntaCard

@Composable
fun BillsDueCard(
    billsCount: Int,
    billsTotalText: String,
    billsDaysUntilDue: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CasaJuntaCard(
        onClick = onClick,
        modifier = modifier.fillMaxHeight(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Description,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.home_bills_title),
                style = MaterialTheme.typography.titleSmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (billsCount == 0) {
                Text(
                    text = stringResource(R.string.home_bills_none),
                    style = MaterialTheme.typography.bodyLarge,
                )
            } else {
                Text(
                    text = pluralStringResource(
                        R.plurals.home_bills_count,
                        billsCount,
                        billsCount,
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = billsTotalText,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = billsDueText(billsCount, billsDaysUntilDue),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Composable
private fun billsDueText(
    billsCount: Int,
    billsDaysUntilDue: Int,
): String {
    if (billsDaysUntilDue == 0) {
        return pluralStringResource(R.plurals.home_bills_due_today, billsCount)
    }
    val inDays = pluralStringResource(
        R.plurals.home_bills_due_in_days,
        billsDaysUntilDue,
        billsDaysUntilDue,
    )
    return pluralStringResource(R.plurals.home_bills_due, billsCount, inDays)
}
