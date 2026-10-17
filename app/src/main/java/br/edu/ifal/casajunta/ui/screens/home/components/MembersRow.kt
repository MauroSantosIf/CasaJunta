package br.edu.ifal.casajunta.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.edu.ifal.casajunta.R
import br.edu.ifal.casajunta.ui.components.MemberAvatar
import br.edu.ifal.casajunta.ui.screens.home.MemberUi

@Composable
fun MembersRow(
    members: List<MemberUi>,
    onAddMemberClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val avatarColor = MaterialTheme.colorScheme.primary
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        members.forEach { member ->
            MemberAvatar(
                name = member.name,
                containerColor = avatarColor,
            )
        }
        AddMemberButton(onClick = onAddMemberClick)
    }
}

@Composable
private fun AddMemberButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.home_cd_add_member),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
