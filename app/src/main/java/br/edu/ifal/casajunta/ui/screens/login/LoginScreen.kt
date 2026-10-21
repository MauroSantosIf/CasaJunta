
package br.edu.ifal.casajunta.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import br.edu.ifal.casajunta.R

@Composable
fun LoginScreen(
    onVoltarClick: () -> Unit,
    onEntrarClick: (String, String) -> Unit = { _, _ -> }
) {
    val colors = MaterialTheme.colorScheme

    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var mostrarSenha by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .safeDrawingPadding()
            .padding(horizontal = 15.dp)
    ) {

        IconButton(
            onClick = onVoltarClick,
            modifier = Modifier.offset(x = (-15).dp)
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = colors.onBackground
            )
        }

        Spacer(Modifier.height(24.dp))


        Image(
            painter = painterResource(R.drawable.casajunta_logo),
            contentDescription = "Logo CasaJunta com coração",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Fit
        )


        Spacer(Modifier.height(20.dp))

        Text(
            text = "Bem-vindo de volta",
            style = MaterialTheme.typography.headlineLarge,
            color = colors.onBackground
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Entre com a conta que você usa na sua casa. Seus dados sincronizam automaticamente.",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "E-mail",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onSurface
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Digite seu e-mail") },
            leadingIcon = {
                Icon(Icons.Outlined.Email, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Senha",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onSurface
        )

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Digite sua senha") },
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = null)
            },
            trailingIcon = {
                IconButton(
                    onClick = { mostrarSenha = !mostrarSenha }
                ) {
                    Icon(
                        imageVector = if (mostrarSenha) {
                            Icons.Outlined.VisibilityOff
                        } else {
                            Icons.Outlined.Visibility
                        },
                        contentDescription = if (mostrarSenha) {
                            "Ocultar senha"
                        } else {
                            "Mostrar senha"
                        }
                    )
                }
            },
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Esqueci minha senha",
            modifier = Modifier
                .align(Alignment.End)
                .clickable { },
            style = MaterialTheme.typography.labelMedium,
            color = colors.primary
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { onEntrarClick(email, senha) },
            enabled = email.isNotBlank() && senha.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            )
        ) {
            Text(
                text = "Entrar",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(Modifier.height(18.dp))
    }
}
