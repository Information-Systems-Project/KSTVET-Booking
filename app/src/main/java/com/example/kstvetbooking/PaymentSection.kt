package com.example.kstvetbooking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.RoomPreferences
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kstvetbooking.data.PaymentsData

var payments = listOf(
    PaymentsData(
        title = "Amount Paid",
        amount = 2000,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    ),

    PaymentsData(
        title = "Balance",
        amount = 2000,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    ),

    PaymentsData(
        title = "Amount To Be Paid",
        amount = 4000,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    )
)

@Preview
@Composable
fun PaymentSection(){
    LazyRow{
        items(payments.size){
            index ->  PayItem(index)

        }
    }
}

@Composable
fun PayItem(index: Int){
    val pay = payments[index]
    Box(modifier = Modifier
        .padding(start = 16.dp)
        .clickable {}){
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(pay.color)
                .width(150.dp)
                .height(130.dp)
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Icon(imageVector = Icons.Rounded.AccountBalance,
                contentDescription = pay.title,
                modifier = Modifier.width(70.dp)
            )

            Text(
                text = pay.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = pay.amount.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

