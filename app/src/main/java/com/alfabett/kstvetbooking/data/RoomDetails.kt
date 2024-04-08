package com.alfabett.kstvetbooking.data

data class RoomDetails(
    val room_name:String = "",
    val room_gender:String = "",
    val bed_status:String = "not booked"
)

data class BookingDetails(
    val user_id:String,
    val room_id: String?,
    val checkin_date: String,
    val checkout_date: String,
    val roo_m_amount:Int = 4000,
    val paid_amount: Int = 0,
    val room_balance: Int
)
