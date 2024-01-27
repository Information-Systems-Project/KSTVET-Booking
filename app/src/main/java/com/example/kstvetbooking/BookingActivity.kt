package com.example.kstvetbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kstvetbooking.components.homecomponents.BottomNavBar
import com.example.kstvetbooking.components.homecomponents.TopSection
import com.example.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class BookingActivity : ComponentActivity() {
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
                    Booking()
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

@Preview
@Composable
fun Booking(){
    Scaffold(
        bottomBar = {
            BottomNavBar()
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TopSection()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "Available Room",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "A01B02",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(fraction = .8f)
                        .clip(RoundedCornerShape(5.dp))
                        .align(Alignment.CenterHorizontally),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(2.dp),
                        text = "PAY NOW",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth(fraction = .8f)
                        .clip(RoundedCornerShape(5.dp))
                        .align(Alignment.CenterHorizontally),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(2.dp),
                        text = "PAY LATER",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }

    }
}

