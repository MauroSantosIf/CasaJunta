package br.edu.ifal.casajunta.ui.screens.tarefas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifal.casajunta.ui.components.CasaJuntaBottomBar
import br.edu.ifal.casajunta.ui.navigation.TopLevelDestination
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun TarefasRoute(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNotificationsClick: () -> Unit,
    onHouseProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TarefasScreen(
        isDarkTheme = isDarkTheme,
        onToggleTheme = onToggleTheme,
        onNotificationsClick = onNotificationsClick,
        onHouseProfileClick = onHouseProfileClick,
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7"
)
@Composable
private fun TarefasComFooterPreview() {
    CasaJuntaTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                CasaJuntaBottomBar(destinations = TopLevelDestination.entries,
                    selected = TopLevelDestination.Tasks,
                    onSelect = {}
                )
            }
        ) {
            innerPadding -> TarefasRoute(
                isDarkTheme = false,
                onToggleTheme = {},
                onNotificationsClick = {},
                onHouseProfileClick = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}