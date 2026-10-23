package br.edu.ifal.casajunta.ui.screens.tarefas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun TarefasScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =Alignment.CenterVertically) {
            Text(
                text = "CasaJunta",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
                )
            Text(text = "\uD83D\uDC64")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =Alignment.CenterVertically ) {
            Text(text = "Tarefas",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold)

            Button(onClick = {}) {
                Text(text = "+ Adicionar tarefa")
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7"
)
@Composable
fun TarefasScreenPreview() {
    CasaJuntaTheme {
        TarefasScreen()
    }
}