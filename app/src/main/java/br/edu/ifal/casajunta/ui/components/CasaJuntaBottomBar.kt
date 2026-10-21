package br.edu.ifal.casajunta.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.ui.navigation.TopLevelDestination
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun CasaJuntaBottomBar(
    destinations: List<TopLevelDestination>,
    selected: TopLevelDestination,
    onSelect: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    val outline = MaterialTheme.colorScheme.outline
    NavigationBar(
        modifier = modifier.drawBehind {
            val strokeWidth = 1.dp.toPx()
            drawLine(
                color = outline,
                start = Offset(0f, strokeWidth / 2f),
                end = Offset(size.width, strokeWidth / 2f),
                strokeWidth = strokeWidth,
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
    ) {
        destinations.forEach { destination ->
            val isSelected = destination == selected
            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelect(destination) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        },
                        contentDescription = null,
                    )
                },
                label = { Text(text = stringResource(destination.labelRes)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CasaJuntaBottomBarPreview() {
    CasaJuntaTheme(darkTheme = isSystemInDarkTheme()) {
        CasaJuntaBottomBar(
            destinations = TopLevelDestination.entries,
            selected = TopLevelDestination.Overview,
            onSelect = { _ -> },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
