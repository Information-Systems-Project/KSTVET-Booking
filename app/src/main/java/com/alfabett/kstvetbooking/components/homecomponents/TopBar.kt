package com.alfabett.kstvetbooking.components.homecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TopBar(onMenuItemClick: (String) -> Unit){
    var expanded by remember{mutableStateOf(false)}
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
            expanded = false
        }) {
            DropdownMenuItem(
                text = { "Profile" },
                onClick = { /*TODO*/ }
            )
            DropdownMenuItem(
                text = { "Logout" },
                onClick = { /*TODO*/ }
            )
        }
    }
}