package br.edu.ifal.casajunta.ui.screens.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R

import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme.extendedColors

private enum class PapelCasa{
    MORADOR,
    CRIAR_CASA,
    ENTRAR_CASA
}


@Composable
fun OnboardingScreen(
    onMoradorClick: () -> Unit,
    onCriarCasaClick: () -> Unit = {},
    onEntrarCasaClick: () -> Unit = {}
) {
    var opcaoSelecionada by remember {
        mutableStateOf<PapelCasa?>(null)
    }
    val colors = MaterialTheme.colorScheme
    val extendedColors = CasaJuntaTheme.extendedColors

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .safeDrawingPadding()
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.casajunta_logo),
                    contentDescription = "Logo CasaJunta",
                    modifier = Modifier.size(44.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    "CasaJunta",
                    style = MaterialTheme.typography.headlineLarge,
                    color = colors.onBackground
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                "Mais que uma casa,\numa vida em conjunto.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = colors.onSurfaceVariant
            )
            Spacer(Modifier.height(36.dp))
            Image(
                painter = painterResource(R.drawable.casajunta_illustration),
                contentDescription = "Casa entre árvores",
                modifier = Modifier.fillMaxWidth().aspectRatio(390f / 230f),
                contentScale = ContentScale.FillBounds
            )
            // A ilustração e o painel se sobrepõem em 24 dp.
            // O espaçamento negativo é implementado com offset apenas no painel;
            // a altura extra compensa o deslocamento para não deixar rodapé vazio.
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-24).dp)
                    .heightIn(min = (screenHeight - 360.dp).coerceAtLeast(450.dp) + 24.dp),
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                color = colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 28.dp, bottom = 26.dp)
                ) {
                    Text(
                        "Qual é o seu papel na casa?",
                        style = MaterialTheme.typography.headlineMedium,
                        color = colors.onSurface
                    )
                    Spacer(Modifier.height(16.dp))
                    OpcaoPapelCard(
                        titulo = "Sou morador(a)",
                        descricao = "Já faço parte da casa",
                        icone = Icons.Default.People,
                        selecionado = opcaoSelecionada == PapelCasa.MORADOR,
                        onClick = { opcaoSelecionada = PapelCasa.MORADOR }
                    )
                    Spacer(Modifier.height(12.dp))
                    OpcaoPapelCard(
                        titulo = "Quero criar uma casa",
                        descricao = "Começar do zero",
                        icone = Icons.Default.Add,
                        selecionado = opcaoSelecionada == PapelCasa.CRIAR_CASA,
                        onClick = { opcaoSelecionada =  PapelCasa.CRIAR_CASA }
                    )
                    Spacer(Modifier.height(12.dp))
                    OpcaoPapelCard(
                        titulo = "Quero entrar em uma casa",
                        descricao = "Tenho um convite",
                        icone = Icons.Outlined.MailOutline,
                        selecionado = opcaoSelecionada == PapelCasa.ENTRAR_CASA,
                        onClick = { opcaoSelecionada = PapelCasa.ENTRAR_CASA }
                    )
                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = {
                            when (opcaoSelecionada) {

                                PapelCasa.MORADOR -> onMoradorClick()
                                PapelCasa.CRIAR_CASA -> onCriarCasaClick()
                                PapelCasa.ENTRAR_CASA -> onEntrarCasaClick()
                                null -> Unit

                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = CircleShape,
                        enabled = opcaoSelecionada != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        )
                    ) {
                        Text("Continuar", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

@Composable
fun OpcaoPapelCard(
    titulo: String,
    descricao: String,
    icone: ImageVector,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
              selected = selecionado,
                onClick = onClick,
                role = Role.RadioButton
            ),

        shape = RoundedCornerShape(16.dp),
        color = if (selecionado) {
            extendedColors.primaryLight
        } else {
            colors.surfaceVariant
        },        border = BorderStroke(1.dp, if (selecionado) colors.primary else colors.outline)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(12.dp),
                color = if (selecionado) {
                    colors.surface
                } else {
                    extendedColors.primaryLight
                }            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icone, contentDescription = null, tint = colors.primary, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(titulo, style = MaterialTheme.typography.titleMedium, color = colors.onSurface)
                Text(descricao, style = MaterialTheme.typography.bodyMedium, color = colors.onSurfaceVariant)
            }
            Box(Modifier.size(22.dp), contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    color = if (selecionado) colors.primary else colors.surface,
                    border = BorderStroke(2.dp, if (selecionado) colors.primary else colors.onSurfaceVariant)
                ) {}
                if (selecionado) {
                    Surface(Modifier.size(10.dp), shape = CircleShape, color = colors.onPrimary) {}
                }
            }
        }
    }
}
