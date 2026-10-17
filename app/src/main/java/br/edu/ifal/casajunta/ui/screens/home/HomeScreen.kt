package br.edu.ifal.casajunta.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.data.home.HomeSummary
import br.edu.ifal.casajunta.ui.screens.home.components.BalanceCard
import br.edu.ifal.casajunta.ui.screens.home.components.BillsDueCard
import br.edu.ifal.casajunta.ui.screens.home.components.HomeHeader
import br.edu.ifal.casajunta.ui.screens.home.components.MembersRow
import br.edu.ifal.casajunta.ui.screens.home.components.PantryCard
import br.edu.ifal.casajunta.ui.screens.home.components.TasksProgressCard
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme
import br.edu.ifal.casajunta.ui.theme.Spacing

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNotificationsClick: () -> Unit,
    onHouseProfileClick: () -> Unit,
    onAddMemberClick: () -> Unit,
    onBalanceClick: () -> Unit,
    onTasksClick: () -> Unit,
    onBillsClick: () -> Unit,
    onPantryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        when (uiState) {
            HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            is HomeUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (constraints.hasBoundedHeight) {
                                Modifier.verticalScroll(scrollState)
                            } else {
                                Modifier
                            },
                        )
                        .padding(horizontal = Spacing.screenHorizontal)
                        .padding(top = 12.dp, bottom = Spacing.sectionGap),
                    verticalArrangement = Arrangement.spacedBy(Spacing.sectionGap),
                ) {
                    HomeHeader(
                        onNotificationsClick = onNotificationsClick,
                        onHouseProfileClick = onHouseProfileClick,
                    )
                    HomeGreeting()
                    MembersRow(
                        members = uiState.members,
                        onAddMemberClick = onAddMemberClick,
                    )
                    BalanceCard(
                        balanceText = uiState.balanceText,
                        onClick = onBalanceClick,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max),
                        horizontalArrangement = Arrangement.spacedBy(Spacing.cardGap),
                    ) {
                        TasksProgressCard(
                            tasksDone = uiState.tasksDone,
                            tasksTotal = uiState.tasksTotal,
                            progress = uiState.tasksProgress,
                            onClick = onTasksClick,
                            modifier = Modifier.weight(1f),
                        )
                        BillsDueCard(
                            billsCount = uiState.billsCount,
                            billsTotalText = uiState.billsTotalText,
                            billsDaysUntilDue = uiState.billsDaysUntilDue,
                            onClick = onBillsClick,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    PantryCard(
                        pantryMissingCount = uiState.pantryMissingCount,
                        onClick = onPantryClick,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeGreeting(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.home_greeting),
            style = MaterialTheme.typography.headlineLarge,
            color = CasaJuntaTheme.extendedColors.primaryDark,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.home_subtitle),
                modifier = Modifier.weight(1f, fill = false),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Icon(
                imageVector = Icons.Filled.Cottage,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(18.dp),
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

internal fun previewHomeUiState(): HomeUiState.Success = toUiState(
    HomeSummary(
        memberNames = listOf("Lívia", "Thiago", "Mauro", "Viviam"),
        balanceCents = 47230L,
        tasksDone = 8,
        tasksTotal = 12,
        billsCount = 2,
        billsTotalCents = 32050L,
        billsDaysUntilDue = 5,
        pantryMissingCount = 3,
    ),
)

@Preview(name = "Default", showBackground = true)
@Preview(name = "Font scale 1.3", showBackground = true, fontScale = 1.3f)
@Preview(name = "Width 360", showBackground = true, widthDp = 360)
@Composable
private fun HomeScreenPreview() {
    CasaJuntaTheme {
        HomeScreen(
            uiState = previewHomeUiState(),
            onNotificationsClick = {},
            onHouseProfileClick = {},
            onAddMemberClick = {},
            onBalanceClick = {},
            onTasksClick = {},
            onBillsClick = {},
            onPantryClick = {},
        )
    }
}
