package com.alfabett.kstvetbooking

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfabett.kstvetbooking.components.homecomponents.BottomNavBar
import com.alfabett.kstvetbooking.components.homecomponents.PaymentSection
import com.alfabett.kstvetbooking.components.homecomponents.TopSection
import com.alfabett.kstvetbooking.db.DbConnect
import com.alfabett.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth


class BookingActivity : ComponentActivity() {
    private val dbvm by viewModels<DbConnect>()
    val user: String? = FirebaseAuth.getInstance().currentUser?.uid
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
                    Booking(dbvm, user.toString())
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
fun Booking(dbConnect: DbConnect, user_id:String){
    dbConnect.getEmptyRoom()
    val context = LocalContext.current
    var available_room by remember {
        mutableStateOf(dbConnect.getEmptyRoom())
    }
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
            TopSection(dbConnect, user_id)
            PaymentSection(dbConnect = dbConnect, user_id = user_id)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "Available Room",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .align(Alignment.CenterHorizontally),
                    text = available_room,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.primary,
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(fraction = .8f)
                        .clip(RoundedCornerShape(5.dp))
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                              context.startActivity(Intent(context, PaymentActivity::class.java))
                    },
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
                    onClick = {
                              context.startActivity(Intent(context, MainActivity::class.java))
                    },
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

