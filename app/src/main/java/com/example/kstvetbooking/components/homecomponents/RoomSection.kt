package com.example.kstvetbooking.components.homecomponents

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
import androidx.compose.material.icons.rounded.RoomPreferences
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kstvetbooking.data.RoomData

val rooms = listOf(
    RoomData(
        title = "Available Rooms",
        number = 200,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    ),
    RoomData(
        title = "Occupied Rooms",
        number = 200,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    ),
    RoomData(
        title = "Booked Room",
        number = 1,
        color = getGradient(Color(0xffb36804), Color(0xffe9b878))
    )
)

fun getGradient(
    startColor: Color,
    endColor: Color): Brush{
    return Brush.verticalGradient(
        colors = listOf(startColor, endColor)
    )

}

@Preview
@Composable
fun RoomSection(){
    LazyRow{
        items(rooms.size){ index ->
            RoomItem(index)
        }
    }
}

@Composable
fun RoomItem(index: Int) {
    val room = rooms[index]
    Box(modifier = Modifier
        .padding(start = 16.dp)
        .clickable {}){
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(room.color)
                .width(150.dp)
                .height(130.dp)
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Icon(imageVector = Icons.Rounded.RoomPreferences,
                contentDescription = room.title,
                modifier = Modifier.width(70.dp)
                )

            Text(
                text = room.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = room.number.toString(),
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
