package com.alfabett.kstvetbooking.components.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfabett.kstvetbooking.RegisterActivity
import com.alfabett.kstvetbooking.data.RegUser
import com.alfabett.kstvetbooking.db.DbConnect

@Composable
fun CredentialSection(dbConnect: DbConnect){

    val context = LocalContext.current
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
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val reg_user = RegUser(
                    email = email.text,
                    password = password.text
                )
                if(reg_user.email.isNotBlank() || reg_user.password.isNotBlank())
                    dbConnect.loginUser(reg_user, context)
                else
                    Toast.makeText(context, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show()
            }
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    context.startActivity(
                        Intent(context, RegisterActivity::class.java)
                    )
                },
            text = "Don\'t Have An Account?, Click Here.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {},
            text = "Forgot Password?, Click Here.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

