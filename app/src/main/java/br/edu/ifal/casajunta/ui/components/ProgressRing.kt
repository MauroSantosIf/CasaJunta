package br.edu.ifal.casajunta.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun ProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 84.dp,
    strokeWidth: Dp = 7.dp,
    center: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = strokeWidth,
            trackColor = CasaJuntaTheme.extendedColors.primaryLight,
            strokeCap = StrokeCap.Round,
            gapSize = 0.dp,
        )
        center()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressRingPreview() {
    CasaJuntaTheme {
        ProgressRing(progress = 8f / 12f) {
            Text(
                text = "8/12",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
