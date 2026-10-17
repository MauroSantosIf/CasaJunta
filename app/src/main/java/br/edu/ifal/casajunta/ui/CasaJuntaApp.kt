package br.edu.ifal.casajunta.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import br.edu.ifal.casajunta.ui.components.CasaJuntaBottomBar
import br.edu.ifal.casajunta.ui.navigation.TopLevelDestination
import br.edu.ifal.casajunta.ui.screens.home.HomeRoute

@Composable
fun CasaJuntaApp() {
    val selectedDestination by rememberSaveable(
        stateSaver = Saver(
            save = { it.name },
            restore = { name -> TopLevelDestination.valueOf(name) },
        ),
    ) {
        mutableStateOf(TopLevelDestination.Overview)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            CasaJuntaBottomBar(
                destinations = TopLevelDestination.entries,
                selected = selectedDestination,
                onSelect = { _ ->
                    // As outras abas ainda não têm tela.
                },
            )
        },
    ) { innerPadding ->
        HomeRoute(modifier = Modifier.padding(innerPadding))
    }
}
