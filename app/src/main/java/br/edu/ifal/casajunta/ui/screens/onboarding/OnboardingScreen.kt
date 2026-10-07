package br.edu.ifal.casajunta.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.theme.CasaJuntaGreen

import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun OnboardingScreen() {

    var opcaoSelecionada by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "CasaJunta",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = "Mais que uma casa,\numa vida em conjunto.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))


        Image(
            painter = painterResource(
                id = R.drawable.onboarding_house
            ),
            contentDescription = "Ilustração de uma casa",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        // Área inferior
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            ),
            color = MaterialTheme.colorScheme.surface
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = "Qual é o seu papel na casa?",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                // OPÇÃO 1
                OpcaoPapelCard(
                    titulo = "Sou morador(a)",
                    descricao = "Já faço parte da casa",
                    icone = Icons.Default.People,
                    selecionado = opcaoSelecionada == "MORADOR",
                    onClick = {
                        opcaoSelecionada = "MORADOR"
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // OPÇÃO 2
                OpcaoPapelCard(
                    titulo = "Quero criar uma casa",
                    descricao = "Começar do zero",
                    icone = Icons.Default.Add,
                    selecionado = opcaoSelecionada == "CRIAR_CASA",
                    onClick = {
                        opcaoSelecionada = "CRIAR_CASA"
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))

                // OPÇÃO 3
                OpcaoPapelCard(
                    titulo = "Quero entrar em uma casa",
                    descricao = "Tenho um convite",
                    icone = Icons.Default.Search,
                    selecionado = opcaoSelecionada == "ENTRAR_CASA",
                    onClick = {
                        opcaoSelecionada = "ENTRAR_CASA"
                    }
                )

                // Botão ded continuar
                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    onClick = {

                    },
                    enabled = opcaoSelecionada != null,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.35f
                        ),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.70f
                        )
                    )
                ) {
                    Text(
                        text = "Continuar"
                    )
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
           .clickable{
               onClick()
           },

       shape = RoundedCornerShape(16.dp),
       color = if(selecionado){
           MaterialTheme.colorScheme.secondaryContainer
       }else{
           MaterialTheme.colorScheme.surfaceVariant
       }
   ) {
        Row(
            modifier = Modifier.padding(16.dp),
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
                contentDescription = "Selecionar Opção",
                tint = CasaJuntaGreen
            )

        }
   }
}