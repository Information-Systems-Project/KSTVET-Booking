package com.example.kstvetbooking.components.login

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LogoSection(){
    Icon(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        imageVector = Icons.Rounded.AccountCircle,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}