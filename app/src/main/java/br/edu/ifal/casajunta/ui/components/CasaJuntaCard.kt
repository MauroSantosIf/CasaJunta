package br.edu.ifal.casajunta.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun CasaJuntaCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.onSurface,
    )
    val elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    val shape = MaterialTheme.shapes.large
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            content = content,
        )
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            colors = colors,
            elevation = elevation,
            content = content,
        )
    }
}

@PreviewLightDark
@Composable
private fun CasaJuntaCardPreview() {
    CasaJuntaTheme(darkTheme = isSystemInDarkTheme()) {
        CasaJuntaCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Card",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}
