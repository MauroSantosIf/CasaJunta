package br.edu.ifal.casajunta.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

@Composable
fun HomeHeader(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNotificationsClick: () -> Unit,
    onHouseProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.home_brand),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineMedium,
            color = CasaJuntaTheme.extendedColors.primaryDark,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onToggleTheme) {
                Icon(
                    imageVector = if (isDarkTheme) {
                        Icons.Rounded.LightMode
                    } else {
                        Icons.Rounded.DarkMode
                    },
                    contentDescription = stringResource(
                        if (isDarkTheme) {
                            R.string.home_theme_to_light
                        } else {
                            R.string.home_theme_to_dark
                        },
                    ),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            IconButton(onClick = onNotificationsClick) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = stringResource(R.string.home_cd_notifications),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            HouseProfileButton(onClick = onHouseProfileClick)
        }
    }
}

@Composable
private fun HouseProfileButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = CasaJuntaTheme.extendedColors.primaryLight,
                    shape = CircleShape,
                )
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Groups,
                contentDescription = stringResource(R.string.home_cd_house_profile),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
