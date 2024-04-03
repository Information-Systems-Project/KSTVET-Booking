package com.alfabett.kstvetbooking.data

data class RoomDetails(
    val room_id:String,
    val room_name:String,
    val room_gender:String,
    val bed_status:Boolean = false
)

data class BookingDetails(
//    val booking_id:String,
    val user_id:String,
    val room_id:String,
    val checkin_date: String,
    val checkout_date: String,
    val room_amount:Int = 4000,
    val paid_amount: Int = 0,
    val room_balance: Int
)
