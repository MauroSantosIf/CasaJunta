package br.edu.ifal.casajunta.ui.screens.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.theme.CasaJuntaGreen



@Composable
fun OnboardingScreen(
    onMoradorClick: () -> Unit
) {
    var opcaoSelecionada by remember {
        mutableStateOf<String?>(null)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo CasaJunta",
                modifier = Modifier.size(96.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "CasaJunta",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.offset(y = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.image_central),
                contentDescription = "Ilustração de uma casa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(245.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Mais que uma casa,\numa vida em conjunto.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    lineHeight = 20.sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )



            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 215.dp),
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                ),
                color = MaterialTheme.colorScheme.surface
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 20.dp,
                            bottom = 24.dp
                        )
                ) {

                    Text(
                        text = "Qual é o seu papel na casa?",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )


                    // Opção: Morador
                    OpcaoPapelCard(
                        titulo = "Sou morador(a)",
                        descricao = "Já faço parte da casa",
                        icone = Icons.Default.People,
                        selecionado = opcaoSelecionada == "MORADOR",
                        onClick = {
                            opcaoSelecionada = "MORADOR"
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    // Opção: Criar casa
                    OpcaoPapelCard(
                        titulo = "Quero criar uma casa",
                        descricao = "Começar do zero",
                        icone = Icons.Default.Add,
                        selecionado = opcaoSelecionada == "CRIAR_CASA",
                        onClick = {
                            opcaoSelecionada = "CRIAR_CASA"
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    // Opção: Entrar em uma casa
                    OpcaoPapelCard(
                        titulo = "Quero entrar em uma casa",
                        descricao = "Tenho um convite",
                        icone = Icons.Default.Search,
                        selecionado = opcaoSelecionada == "ENTRAR_CASA",
                        onClick = {
                            opcaoSelecionada = "ENTRAR_CASA"
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )



                    Button(
                        onClick = {
                            if(opcaoSelecionada == "MORADOR"){
                                onMoradorClick()
                            }
                        },
                        enabled = opcaoSelecionada != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor =
                                MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.35f
                                ),
                            disabledContentColor =
                                MaterialTheme.colorScheme.onPrimary.copy(
                                    alpha = 0.70f
                                )
                        )
                    ) {

                        Text(
                            text = "Continuar",
                            style = MaterialTheme.typography.labelLarge
                        )
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

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = if (selecionado) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        border = if (selecionado) {
            BorderStroke(
                width = 1.dp,
                color = CasaJuntaGreen
            )
        } else {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
        }
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Icon(
                imageVector = icone,
                contentDescription = titulo,
                tint = CasaJuntaGreen
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Selecionar opção",
                tint = CasaJuntaGreen
            )
        }
    }
}