package com.alfabett.kstvetbooking.components.register

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AdminPanelSettings
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Transgender
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
import com.alfabett.kstvetbooking.LoginActivity
import com.alfabett.kstvetbooking.db.DbConnect

@Composable
fun InputSection(dbConnect: DbConnect){

    val context = LocalContext.current
    var user_name by remember {
        mutableStateOf(TextFieldValue())
    }

    var adm_no by remember {
        mutableStateOf(TextFieldValue())
    }

    var email by remember {
        mutableStateOf(TextFieldValue())
    }

    var phone_number by remember {
        mutableStateOf(TextFieldValue())
    }

    var gender by remember {
        mutableStateOf(TextFieldValue())
    }

    var password by remember {
        mutableStateOf(TextFieldValue())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = user_name,
            label = {
                Text(text = "Full Names")
            },
            onValueChange = {user_n ->
                user_name = user_n
            },
            placeholder = {
                Text(text = "Enter Your Full Names")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.AccountCircle,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = email,
            label = {
                Text(text = "Email Address")
            },
            onValueChange = { email_n ->
                email = email_n
            },
            placeholder = {
                Text(text = "Enter Your Email Address")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = adm_no,
            label = {
                Text(text = "Admission Number")
            },
            onValueChange = {adm_n ->
                adm_no = adm_n
            },
            placeholder = {
                Text(text = "Enter Your Admission Number")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.AdminPanelSettings,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = phone_number,
            label = {
                Text(text = "Phone Number")
            },
            onValueChange = {phone_n ->
                phone_number = phone_n
            },
            placeholder = {
                Text(text = "Enter Your Admission Number")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Phone,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = gender,
            label = {
                Text(text = "Gender")
            },
            onValueChange = {gender_n ->
                gender = gender_n
            },
            placeholder = {
                Text(text = "Enter Your Gender")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Transgender,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = password,
            label = {
                Text(text = "Password")
            },
            onValueChange = {password_n ->
                password = password_n
            },
            placeholder = {
                Text(text = "Enter Your Password")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email,
                    contentDescription =null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = {
                dbConnect.regUser(email.toString(), password.toString(), context)
            }
        ) {
            Text(text = "Log In")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    context.startActivity(
                        Intent(context, LoginActivity::class.java)
                    )
                },
            text = "Have An Account?, Click Here.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}