package com.alfabett.kstvetbooking.data

import androidx.compose.ui.graphics.Brush
import com.alfabett.kstvetbooking.db.DbConnect

data class PaymentsData(
    var title: String,
    var amount: Int,
    var color: Brush,
)

var dbConnect = DbConnect()

