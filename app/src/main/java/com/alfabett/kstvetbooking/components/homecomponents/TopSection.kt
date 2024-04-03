package com.alfabett.kstvetbooking.components.homecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfabett.kstvetbooking.db.DbConnect

@Composable
fun TopSection(db_connect: DbConnect){
    db_connect.getUserDetails()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = "Student Name: ${db_connect.username}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
            )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "ADM No. ${db_connect.adm}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}