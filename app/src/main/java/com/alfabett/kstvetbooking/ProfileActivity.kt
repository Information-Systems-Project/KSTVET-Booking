package com.alfabett.kstvetbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfabett.kstvetbooking.db.DbConnect
import com.alfabett.kstvetbooking.ui.theme.KSTVETBookingTheme
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : ComponentActivity() {
    val dbvm by viewModels<DbConnect>()
    val user = FirebaseAuth.getInstance().currentUser?.uid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KSTVETBookingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userProfile(dbConnect = dbvm, user_id = user.toString())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (user != null) {
            dbvm.getUserProfile(user)
        }
    }
}

//@Preview
@Composable
fun userProfile(dbConnect: DbConnect, user_id:String){
//    dbConnect.getUserProfile(user_id)
    val (name, email, adm, phone, checkin, checkout, room) = dbConnect.getUserProfile(user_id)!!
    val (uname, uadm) = dbConnect.getUserDetails(user_id)
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        contentAlignment = Alignment.BottomStart
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
//            Icon(imageVector = Icons.Rounded., contentDescription = "User Image")

            Text(
                text = "Student Name: $uname",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Student Admission Number: $uadm",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Student Email Address $email",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Student Phone Number  $phone",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Room Booked  $room",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Check In Date  $checkin",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Checkout Date  $checkout",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = {
                    dbConnect.userLogout(context)
            }) {
                Text(text = "Log Out")
            }
        }
    }
}
