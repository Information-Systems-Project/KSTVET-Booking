package com.example.kstvetbooking.components.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun CredentialSection(){
    var email by remember {
        mutableStateOf(TextFieldValue())
    }

    var password by remember {
        mutableStateOf(TextFieldValue())
    }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = email,
            label = {
                    Text(text = "Email Address")
            },
            onValueChange = {input ->
                email = input
            },
            placeholder = {
                Text(text = "Enter Your Email Address")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = password,
            label = {
                Text(text = "Password")
            },
            onValueChange = {pwd ->
                password = pwd
            },
            placeholder = {
                Text(text = "Enter Your Password")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Lock,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
    }
}