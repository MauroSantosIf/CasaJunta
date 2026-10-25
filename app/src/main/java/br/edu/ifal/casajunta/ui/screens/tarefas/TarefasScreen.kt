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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme
import br.edu.ifal.casajunta.ui.screens.home.components.HomeHeader
import br.edu.ifal.casajunta.ui.theme.Spacing
import androidx.compose.material3.ButtonDefaults
import br.edu.ifal.casajunta.ui.components.CasaJuntaCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


data class Tarefa(
        val titulo: String,
        val responsavel: String,
        val prazo: String,
        val prioridade: String
        )
@Composable
fun TarefasScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNotificationsClick: () -> Unit,
    onHouseProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var abaSelecionada by remember { mutableIntStateOf(0) }

    val tarefas = listOf(
        Tarefa("Lavar a louça", "Camila", "Hoje", "Normal"),
        Tarefa("Varrer a sala", "Rafael", "Hoje", "Baixa"),
        Tarefa("Limpar o banheiro", "Pedro", "Hoje", "Normal"),
        Tarefa("Tirar o lixo", "Cauã", "Hoje", "Alta")
    )

    Column(modifier = modifier.fillMaxSize()
    .padding(horizontal = Spacing.screenHorizontal)
    .padding(top = 12.dp, bottom = Spacing.sectionGap)
    ) {
        HomeHeader(
            isDarkTheme = isDarkTheme,
            onToggleTheme = onToggleTheme,
            onNotificationsClick = onNotificationsClick,
            onHouseProfileClick = onHouseProfileClick
        )

        Spacer(modifier = Modifier.height(Spacing.sectionGap))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =Alignment.CenterVertically
        ) {

            Text(text = "Tarefas",
                style = MaterialTheme.typography.headlineLarge,
                color = CasaJuntaTheme.extendedColors.primaryDark)

            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFE1DD),
                    contentColor = Color(0xFFA8403A)
                )
            ) {
                Text(text = "+ Adicionar tarefa")
            }
        }

        Spacer(modifier = Modifier.height(Spacing.sectionGap))

        CasaJuntaCard(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Esta semana",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "8 de 12 concluídas",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF073B4C)
                    )

                    Text(
                        text = "67%",
                        style = MaterialTheme.typography.headlineMedium,
                        color = CasaJuntaTheme.extendedColors.primaryDark
                    )
                }

                LinearProgressIndicator(
                    progress = { 0.67f },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = Color(0xFFD9F0E8),
                    drawStopIndicator = {},
                    gapSize = 0.dp)
            }
        }

        Spacer(modifier = Modifier.height(Spacing.sectionGap))

        Row(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(33.dp))
                .background(Color(0xFFE8F2F0))
                .padding(4.dp)
        ) {
            listOf("Hoje", "Semana").forEachIndexed { index, titulo ->

                val selecionada = abaSelecionada == index

                Box(
                    modifier = Modifier.weight(1f)
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            if (selecionada) Color(0xFF007C78)
                            else Color.Transparent
                        )
                        .clickable { abaSelecionada = index }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = titulo,
                        color = if (selecionada) {
                            Color.White
                        } else {
                            Color.Gray
                        },
                        fontWeight = if (selecionada) {
                            FontWeight.SemiBold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Spacing.sectionGap))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Pendentes",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF073B4C)
            )

            Text(
                text = "4 tarefas",
                fontSize = 12.sp,
                color = Color(0xFF52666D)
            )
        }

        Spacer(modifier = Modifier.height(Spacing.sectionGap))

        Column(verticalArrangement = Arrangement.spacedBy(Spacing.cardGap)
        ) {
            tarefas.forEach{ tarefa ->
                TarefaCard(tarefa = tarefa)
            }
        }

    }

}

@Composable
fun TarefaCard(tarefa: Tarefa) {

    val (corFundo, corTexto) = when (tarefa.prioridade.lowercase()) {
        "alta" -> Color(0xFFFFE1DD) to Color(0xFFA8403A)
        "normal" -> Color(0xFFD9F1EC) to Color(0xFF007C78)
        "baixa" -> Color(0xFFFFF3CD) to Color(0xFF946200)
        else -> Color(0xFFE8F2F0) to Color(0xFF52666D)
    }

    CasaJuntaCard(modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.cardGap)
        ) {

            Box(modifier = Modifier.size(36.dp)
                .background(color = Color(0xFF007C78),
                    shape = CircleShape
                ),
                contentAlignment = Alignment.Center) {

                Text(
                    text = tarefa.responsavel.take(1).uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                Text(
                    text = tarefa.titulo,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF073B4C)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.cardGap)
                ) {

                    Text(
                        text = tarefa.responsavel,
                        fontSize = 12.sp,
                        color = Color(0xFF52666D)
                    )

                    Text(
                        text = "·",
                        fontSize = 12.sp,
                        color = Color(0xFF52666D)
                    )

                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(13.dp),
                        tint = Color(0xFF52666D)
                    )

                    Text(
                        text = tarefa.prazo,
                        fontSize = 12.sp,
                        color = Color(0xFF52666D)
                    )
                }
            }

            Column(modifier = Modifier.width(61.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Spacing.cardGap)
            ) {

                Box(modifier = Modifier.size(20.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF52666D),
                        shape = RoundedCornerShape(5.dp)
                    )
                )

                Box(modifier = Modifier.background(color = corFundo,
                    shape = RoundedCornerShape(50))
                    .padding(
                        horizontal = 13.dp,
                        vertical = 0.dp
                    )
                ) {
                    Text(
                        text = tarefa.prioridade,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = corTexto
                    )
                }
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
        TarefasScreen(
            isDarkTheme = false,
            onToggleTheme = {},
            onNotificationsClick = {},
            onHouseProfileClick = {}
        )
    }
}