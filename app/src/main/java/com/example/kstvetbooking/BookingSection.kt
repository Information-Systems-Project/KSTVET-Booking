package com.example.kstvetbooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun BookingSection(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ) {
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
                    text = "BOOK A ROOM",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                    text = "PAY BALANCE",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                    text = "EXIT ROOM",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}