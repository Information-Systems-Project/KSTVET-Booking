package com.alfabett.kstvetbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alfabett.kstvetbooking.components.register.InputSection
import com.alfabett.kstvetbooking.db.DbConnect
import com.alfabett.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class RegisterActivity : ComponentActivity() {
    private val dbvm by viewModels<DbConnect>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
        setContent {
            KSTVETBookingTheme {
                // A surface container using the 'background' color from the theme
                setBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterScreen(dbvm)
                }
            }
        }
    }

    @Composable
    private fun setBarColor(color: Color){
        var systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}


@Composable
fun RegisterScreen(dbConnect: DbConnect){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        InputSection(dbConnect)
        Spacer(modifier = Modifier.height(8.dp))
//        RegButtonSection()
    }
}
