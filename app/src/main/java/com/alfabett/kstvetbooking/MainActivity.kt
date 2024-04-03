package com.alfabett.kstvetbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alfabett.kstvetbooking.components.homecomponents.BookingSection
import com.alfabett.kstvetbooking.components.homecomponents.BottomNavBar
import com.alfabett.kstvetbooking.components.homecomponents.PaymentSection
import com.alfabett.kstvetbooking.components.homecomponents.RoomSection
import com.alfabett.kstvetbooking.components.homecomponents.TopSection
import com.alfabett.kstvetbooking.db.DbConnect
import com.alfabett.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
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
                    HomeScreen(dbvm)
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
fun HomeScreen(db_connect:DbConnect){
    Scaffold (
        bottomBar = {
            BottomNavBar()
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TopSection(db_connect)
            RoomSection()
            Spacer(modifier = Modifier.height(32.dp))
            PaymentSection()
            Spacer(modifier = Modifier.height(32.dp))
            BookingSection()
        }
//
    }
}
