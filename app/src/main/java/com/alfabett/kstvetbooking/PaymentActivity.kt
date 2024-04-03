package com.alfabett.kstvetbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.rounded.Payment
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.alfabett.kstvetbooking.components.homecomponents.BottomNavBar
import com.alfabett.kstvetbooking.db.DbConnect
import com.alfabett.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class PaymentActivity : ComponentActivity() {
    private val dbvm by viewModels<DbConnect>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KSTVETBookingTheme {
                // A surface container using the 'background' color from the theme
                setBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PaymentPage(dbvm)
                }
            }
        }
    }

    @Composable
    private fun setBarColor(color: Color){
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}

@Composable
fun PaymentPage(db_connect:DbConnect){

    var amount by remember{
        mutableStateOf(TextFieldValue())
    }

    var transaction_code by remember{
        mutableStateOf(TextFieldValue())
    }

    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = amount,
                label = {
                    Text(text = "Amount Paid")
                },
                onValueChange = {input ->
                    amount = input
                },
                placeholder = {
                    Text(text = "Enter The Amount")
                },
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Payment,
                        contentDescription =null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = transaction_code,
                label = {
                    Text(text = "Transaction Code")
                },
                onValueChange = { tr_code ->
                    transaction_code = tr_code
                },
                placeholder = {
                    Text(text = "Enter The Transaction Code")
                },
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Payments,
                        contentDescription =null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    db_connect.book_room(amount.text.toInt(), context)
//                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            ) {
                Text(text = "MAKE PAYMENT")
                Spacer(modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.AddCard, contentDescription = null)
            }
        }
    }
}

