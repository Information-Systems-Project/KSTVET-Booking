package com.alfabett.kstvetbooking.components.homecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfabett.kstvetbooking.db.DbConnect

@Composable
fun PaymentSection(dbConnect:DbConnect, user_id: String){
    dbConnect.getBookingDetails(user_id)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Text(text = "Amount Paid:",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = dbConnect.amount_paid.toString(),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(5.dp))

        Row {
            Text(text = "Balance:",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = dbConnect.balance.toString(),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(5.dp))

        Row {
            Text(text = "Amount Be Paid:",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary)
            Text(text = "4000",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)
        }
    }
}