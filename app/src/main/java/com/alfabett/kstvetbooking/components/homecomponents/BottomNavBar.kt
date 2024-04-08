package com.alfabett.kstvetbooking.components.homecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alfabett.kstvetbooking.data.BottonBar

val items = listOf(
    BottonBar(
        title = "Home",
        icon = Icons.Rounded.Home
    ),

//    BottonBar(
//        title = "History",
//        icon = Icons.Rounded.History
//    ),
//
//    BottonBar(
//        title = "Profile",
//        icon = Icons.Rounded.AccountCircle
//    ),
)

@Composable
fun BottomNavBar(){
    NavigationBar {
        Row (modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
        ){
            items.forEachIndexed { index, item ->
                NavigationBarItem(selected = index == 0,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                )
            }
        }
    }
}