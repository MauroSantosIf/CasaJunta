
package br.edu.ifal.casajunta.ui.screens.createHouse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateHouseScreen() {
    var nomeCasa by rememberSaveable { mutableStateOf("") }
    var tipoResidencia by rememberSaveable { mutableStateOf("") }
    var capacidade by rememberSaveable { mutableStateOf("") }
    var metaSemanal by rememberSaveable { mutableStateOf("") }
    var menuExpandido by rememberSaveable { mutableStateOf(false) }

    var cep by rememberSaveable { mutableStateOf("") }
    var rua by rememberSaveable { mutableStateOf("") }
    var numero by rememberSaveable { mutableStateOf("") }
    var complemento by rememberSaveable { mutableStateOf("") }
    var bairro by rememberSaveable { mutableStateOf("") }
    var cidade by rememberSaveable { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf("") }

    var descricao by rememberSaveable { mutableStateOf("") }

    val tiposResidencia = listOf(
        "Casa",
        "Apartamento",
        "República",
        "Outro"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Vamos criar sua casa?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Preencha as informações da residência.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // Título da seção
                Text(
                    text = "Informações da casa",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // 1. Nome da casa
                OutlinedTextField(
                    value = nomeCasa,
                    onValueChange = { nomeCasa = it },
                    label = { Text("Nome da casa") },
                    placeholder = {
                        Text("Ex.: República Mandacaru")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // 2. Tipo de residência
                ExposedDropdownMenuBox(
                    expanded = menuExpandido,
                    onExpandedChange = {
                        menuExpandido = it
                    }
                ) {
                    OutlinedTextField(
                        value = tipoResidencia,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Tipo de residência")
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = menuExpandido
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = menuExpandido,
                        onDismissRequest = {
                            menuExpandido = false
                        }
                    ) {
                        tiposResidencia.forEach { tipo ->
                            DropdownMenuItem(
                                text = { Text(tipo) },
                                onClick = {
                                    tipoResidencia = tipo
                                    menuExpandido = false
                                }
                            )
                        }
                    }
                }

                // 3 e 4. Capacidade e meta semanal
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // 3. Capacidade de moradores
                    OutlinedTextField(
                        value = capacidade,
                        onValueChange = { valor ->
                            capacidade = valor.filter {
                                it.isDigit()
                            }
                        },
                        label = { Text("Capacidade") },
                        placeholder = { Text("Ex.: 6") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    // 4. Meta semanal de pontos
                    OutlinedTextField(
                        value = metaSemanal,
                        onValueChange = { valor ->
                            metaSemanal = valor.filter {
                                it.isDigit()
                            }
                        },
                        label = { Text("Meta semanal") },
                        placeholder = { Text("Ex.: 2000") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Localização",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = cep,
                    onValueChange = { valor ->
                        cep = valor.filter { it.isDigit() }.take(8)
                    },
                    label = { Text("CEP") },
                    placeholder = { Text("00000000") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = rua,
                    onValueChange = { rua = it },
                    label = { Text("Rua / Logradouro") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = numero,
                        onValueChange = { numero = it },
                        label = { Text("Número") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = complemento,
                        onValueChange = { complemento = it },
                        label = { Text("Complemento") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = bairro,
                    onValueChange = { bairro = it },
                    label = { Text("Bairro") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = cidade,
                        onValueChange = { cidade = it },
                        label = { Text("Cidade") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = estado,
                        onValueChange = { valor ->
                            estado = valor
                                .filter { it.isLetter() }
                                .uppercase()
                                .take(2)
                        },
                        label = { Text("UF") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Personalização",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Área visual para foto da residência
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddPhotoAlternate,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp)
                        )

                        Text(
                            text = "Adicionar foto da casa",
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = "Opcional",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Descrição da residência
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição da casa") },
                    placeholder = {
                        Text("Conte um pouco sobre a residência...")
                    },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )

                // Informação sobre o responsável
                Text(
                    text = "Ao criar a casa, você será definido como responsável e poderá convidar outros moradores.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        // Botão principal do formulário
        Button(
            onClick = {
                // Futuramente vamos validar e salvar a casa.
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar minha casa")
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 400,
    heightDp = 1300
)
@Composable
fun CreateHouseScreenPreview() {
    CasaJuntaTheme {
        CreateHouseScreen()
    }
}
